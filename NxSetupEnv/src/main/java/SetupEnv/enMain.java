package SetupEnv;

import org.apache.logging.log4j.LogManager;


/**
 * Created by root on 1/8/17.
 */
public class enMain {

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public static void main(String [] args){

        Arguments myArgs = new Arguments(args, DefaultSettings.appName);
        myArgs.handle();
        if (myArgs.myOptions.logDirectory != null){
            ConfigLogger.updateXmlConfig(DefaultSettings.appName, myArgs.myOptions.debug, myArgs.myOptions.logDirectory);
        }else {
            if (myArgs.myOptions.debug){
                ConfigLogger.setRootLevelDebug();
            }
        }

        GenerateTopology.generateTopology(myArgs);

        logger.info("\""+DefaultSettings.appName+"\" has been started.");
        logger.debug("Pass the topology file \""+myArgs.myOptions.topology+"\" to TopologyConfig class.");
        TopologyConfig topologyConfig = new TopologyConfig(myArgs.myOptions.topology);
        topologyConfig.readTopology();

        TaskManager.startTasks(myArgs, topologyConfig.topologyMap);




    }


}
