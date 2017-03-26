package SetupEnv;

import org.apache.logging.log4j.LogManager;

import java.util.List;

/**
 * Created by root on 1/10/17.
 */
public class RunnableTask implements Runnable {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    private Thread thread;
    private String threadName;
    private DeviceData deviceData;
    private List<String> preCmdListBeforeTopology = null;
    private List<String> postCmdListAfterTopology = null;


    public RunnableTask(String threadName, DeviceData deviceData){
        this.threadName = threadName;
        this.deviceData = deviceData;
    }

    public void setPreCmdListBeforeTopology(List<String> cmdList){
        this.preCmdListBeforeTopology = cmdList;
    }

    public void setPostCmdListAfterTopology(List<String> cmdList){
        this.postCmdListAfterTopology = cmdList;
    }




    public void run() {
        logger.info("("+this.threadName+") Starting the task of thread \""+this.threadName+"\" .");
        logger.debug("("+this.threadName+") Reading the DeviceData of \""+this.threadName+"\" .");
        String ip = this.deviceData.regularOptionsMap.get("ip");
        int port = Integer.parseInt(this.deviceData.regularOptionsMap.get("port"));
        String username = this.deviceData.regularOptionsMap.get("username");
        String password = this.deviceData.regularOptionsMap.get("password");
        logger.debug("("+this.threadName+") Create SshManager instance via options: ip="+ip+" port="+port+" username="+username+" password="+password);
        SshManager sshManager = new SshManager(ip, port, username, password);
        logger.info("("+this.threadName+") Connecting the destination ...");
        sshManager.connect();
        logger.info("("+this.threadName+") Connection has been established.");

        logger.info("("+this.threadName+") Clean and reinit the destination machine.");
        ClearLinuxNetwork cleaner = new ClearLinuxNetwork(sshManager);
        cleaner.clean();
        logger.info("("+this.threadName+") Clean destination configuration over.");


        // --------------------------------------------------------------
        // Pre configuration before topology file
        if (this.preCmdListBeforeTopology != null){
            logger.info("("+this.threadName+") Start to execute the commands in pre config before topology file.");
            for (String cmd: this.preCmdListBeforeTopology){
                logger.info("("+this.threadName+") Sending the command \""+cmd+"\" .");
                sshManager.sendCommand(cmd);
            }
        }


        // --------------------------------------------------------------


        for (String cmd: this.deviceData.commandsList){
            logger.info("("+this.threadName+") Sending the command \""+cmd+"\" .");
            sshManager.sendCommand(cmd);
        }

        // --------------------------------------------------------------
        // Post configuration after topology file
        if (this.postCmdListAfterTopology != null){
            logger.info("("+this.threadName+") Start to execute the commands in post config after topology file.");
            for (String cmd: this.postCmdListAfterTopology){
                logger.info("("+this.threadName+") Sending the command \""+cmd+"\" .");
                sshManager.sendCommand(cmd);
            }

        }

        // --------------------------------------------------------------



        logger.info("("+this.threadName+") Trying to close the connection.");
        sshManager.close();

    }

    public void start(){
        logger.info("("+this.threadName+") Starting the thread \""+this.threadName+"\" .");
        if (thread == null){
            thread = new Thread(this, this.threadName);
            thread.start();
        }
    }

    public void join(){
        if (this.thread != null){
            try {
                logger.info("("+this.threadName+") Join the thread.");
                this.thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("("+this.threadName+") Thread has been over.");
    }

}
