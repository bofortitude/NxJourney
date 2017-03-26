package SetupEnv;

import com.jcraft.jsch.*;
import org.apache.logging.log4j.LogManager;

import javax.security.auth.callback.CallbackHandler;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by root on 1/9/17.
 */
public class SshManager {

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    private String remoteHost;
    private int remotePort;
    private String username;
    private String password;
    private JSch jschSSHChannel;
    private Session sesConnection;
    private int intTimeOut;
    private int retry;

    public SshManager(String host, int dstPort, String username, String password, int intTimeOut, int retry){
        this.remoteHost = host;
        this.remotePort = dstPort;
        this.username = username;
        this.password = password;
        this.intTimeOut = intTimeOut;
        this.retry = retry;
        this.jschSSHChannel = new JSch();
    }

    public SshManager(String host, int dstPort, String username, String password){
        this(host, dstPort, username, password, 60000, 10);
    }

    public boolean connect(){

        logger.debug("Connecting \""+this.remoteHost+"\" ...");
        for (int i=0; i<this.retry ; i++) {
            try {
                sesConnection = jschSSHChannel.getSession(this.username,
                        this.remoteHost, this.remotePort);
                sesConnection.setPassword(this.password);
                // UNCOMMENT THIS FOR TESTING PURPOSES, BUT DO NOT USE IN PRODUCTION
                // sesConnection.setConfig("StrictHostKeyChecking", "no");
                sesConnection.setConfig("StrictHostKeyChecking", "no");
                sesConnection.connect(intTimeOut);
                logger.debug("Connecting "+this.remoteHost+"\" successfully.");
                return true;

            } catch (JSchException jschX) {
                String errorMessage = jschX.getMessage();
                logger.error("Connecting "+this.remoteHost+"\" meets error:");
                logger.error(errorMessage);
                if (i>=9){
                    logger.warn("Trying to connect \""+this.remoteHost+"\" "+this.retry+" times, it still failed, give up connecting.");
                    return false;
                }

            }

        }
        return false;
    }

    public String sendCommand(String command){
        logger.debug("Sending command \""+command+"\" to \""+this.remoteHost+"\" ...");
        StringBuilder outputBuffer = new StringBuilder();

        if (!this.sesConnection.isConnected()){
            logger.debug("The connection to \""+this.remoteHost+"\" is down, trying to reconnect ...");
            if (!this.connect()){
                return null;
            }
        }

        try
        {
            Channel channel = this.sesConnection.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);
            InputStream commandOutput = channel.getInputStream();
            channel.connect();
            int readByte = commandOutput.read();

            while(readByte != 0xffffffff)
            {
                outputBuffer.append((char)readByte);
                readByte = commandOutput.read();
            }

            channel.disconnect();
        }
        catch(IOException ioX)
        {
            logger.error("Sending command \""+command+"\" to \""+this.remoteHost+"\" meets IOExecption:");
            logger.error(ioX.getMessage());
            return null;
        }
        catch(JSchException jschX)
        {
            logger.error("Sending command \""+command+"\" to \""+this.remoteHost+"\" meets JSchException:");
            logger.error(jschX.getMessage());
            return null;
        }

        logger.debug("Resonse from \""+this.remoteHost+"\" :\n"+outputBuffer);
        return outputBuffer.toString();
    }

    public void checkConnection(){
        logger.debug("Checking if the connection to \""+this.remoteHost+"\" is alive.");
        if (!this.sesConnection.isConnected()){
            logger.debug("The connection to \""+this.remoteHost+"\" is down, trying to reconnect.");
            this.connect();
        }else {
            logger.debug("The connection to \""+this.remoteHost+"\" is alive.");
        }
    }

    public String getHost(){
        this.checkConnection();
        return this.sesConnection.getHost().toString();
    }

    public void close()
    {
        this.sesConnection.disconnect();
        logger.debug("The connection to \""+this.remoteHost+"\" has been closed.");
    }


}
