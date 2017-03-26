package SetupEnv;


import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 1/10/17.
 */
public class TaskManager {

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public static List<String> getCmdListFromConfigFile(String configFileToBeRead){
        List<String> cmdList = new ArrayList<String>();

        logger.debug("Starting to read config file "+configFileToBeRead+" ...");
//        File file = new File(configFileToBeRead);
        InputStream inStream = TaskManager.class.getClassLoader().getResourceAsStream(configFileToBeRead);
        BufferedReader reader = null;
        try {

//            reader = new BufferedReader(new FileReader(file));
            reader = new BufferedReader(new InputStreamReader(inStream));
            String tempString = null;
            int line = 1;

            while ((tempString = reader.readLine()) != null) {
//                    System.out.println("line " + line + ": " + tempString);
                if (!tempString.equals("")){
                    logger.debug("Current line :"+tempString);
                    cmdList.add(tempString);
                }
                line++;
            }

            reader.close();
        } catch (FileNotFoundException e) {
            logger.error("Opening file \""+configFileToBeRead+"\" meets FileNotFoundException error!");
            e.printStackTrace();
//            System.exit(2);
        } catch (IOException e){
            logger.error("Opening file \""+configFileToBeRead+"\" meets IOException error!");
            e.printStackTrace();
//            System.exit(2);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    logger.warn("CLosing the file \""+configFileToBeRead+"\" meets IOExceptions error!");
//                    System.exit(2);
                }
            }

        }

        logger.debug("The command list is : "+cmdList);
        return cmdList;

    }




    public static void startTasks(Arguments arguments, Map<String, DeviceData> topologyMap){
        List<RunnableTask> threadList = new ArrayList<RunnableTask>();

        logger.info("Read pre config before topology.");
        List<String> preCmdListBeforeTopology = getCmdListFromConfigFile(DefaultSettings.preConfigBeforeTopologyFile);

        logger.info("Read post config after topology.");
        List<String> postCmdListAfterTopology =  getCmdListFromConfigFile(DefaultSettings.postConfigAfterTopologyFile);

        logger.info("Creating thread instance.");
        RunnableTask currentThread;
        for (Map.Entry<String, DeviceData> e: topologyMap.entrySet()){
             currentThread = new RunnableTask(e.getKey().toString(), e.getValue());
             currentThread.setPreCmdListBeforeTopology(preCmdListBeforeTopology);
             currentThread.setPostCmdListAfterTopology(postCmdListAfterTopology);
//            threadList.add(new RunnableTask(e.getKey().toString(), e.getValue()));
            threadList.add(currentThread);
            logger.debug("The instance for \""+e.getKey()+"\" has been created.");
        }

        logger.info("Start all the threads.");
        for (RunnableTask task: threadList){
            task.start();
        }

        logger.debug("Wait for the threads.");
        for (RunnableTask task: threadList){
            task.join();
        }
    }


}
