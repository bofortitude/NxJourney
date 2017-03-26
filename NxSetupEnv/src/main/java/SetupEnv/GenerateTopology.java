package SetupEnv;

import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 1/10/17.
 */
public class GenerateTopology {

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public static void generateTopology(Arguments myArgs){


        logger.debug("Bengin the process of generating topology example.");
        if (myArgs.myOptions.shellCommandsFile != null){
            logger.info("Starting to generate topology example.");

            logger.info("Open the file "+myArgs.myOptions.shellCommandsFile);
            List<String> cmdLineList = new ArrayList<String>();
            File file = new File(myArgs.myOptions.shellCommandsFile);
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String tempString = null;
                int line = 1;

                while ((tempString = reader.readLine()) != null) {
//                    System.out.println("line " + line + ": " + tempString);
                    cmdLineList.add("cmd"+line*10+"="+tempString);
                    line++;
                }

                reader.close();
            } catch (FileNotFoundException e) {
                logger.error("Opening file \""+myArgs.myOptions.shellCommandsFile+"\" meets FileNotFoundException error!");
                e.printStackTrace();
                System.exit(2);
            } catch (IOException e){
                logger.error("Opening file \""+myArgs.myOptions.shellCommandsFile+"\" meets IOException error!");
                e.printStackTrace();
                System.exit(2);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                        logger.warn("CLosing the file \""+myArgs.myOptions.shellCommandsFile+"\" meets IOExceptions error!");
                        System.exit(2);
                    }
                }

            }

            System.out.println("");
            System.out.println("[Server1]");
            System.out.println("ip=10.0.12.21");
            System.out.println("username=root");
            System.out.println("password=fortinet");
            for (String i: cmdLineList){
                System.out.println(i);
            }
            System.out.println("");

            System.exit(1);
        }else {
            if (myArgs.myOptions.genTopologyExample){
                System.out.println("");
                System.out.println("[Server1]");
                System.out.println("ip=10.0.12.21");
                System.out.println("username=root");
                System.out.println("password=fortinet");
                System.out.println("cmd10=ip link add link eth1 name eth1.1101 type vlan id 1101");
                System.out.println("cmd20=ip link set eth1.1101 up");
                System.out.println("cmd30=ip add add 10.78.12.21/16 dev eth1.1101");
                System.out.println("");

                System.exit(1);
            }
        }

    }


}
