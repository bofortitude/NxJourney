package SetupEnv;


import org.apache.logging.log4j.LogManager;
import org.ini4j.Profile;
import org.ini4j.Wini;


import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by root on 1/8/17.
 */
public class TopologyConfig {

    public Map<String, DeviceData> topologyMap = new HashMap<String, DeviceData>();
    private String topologyFile;
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    private Properties props;


    public TopologyConfig(String topologyFile){
        this.topologyFile = topologyFile;
    }

    private void setValue(String sectionName, String key, String defaultValue){
        if (!this.topologyMap.get(sectionName).regularOptionsMap.containsKey(key)){
            this.topologyMap.get(sectionName).regularOptionsMap.put(key, defaultValue);
            logger.debug("\""+key+"\" of \""+sectionName+"\" in topology file is not given, set the default value \""+defaultValue+"\" for it.");
        }
    }

    private void readDefaultValueConfig(){
        Properties props = new Properties();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(DefaultSettings.topologyDefaultValueConfigFile);
        try {
            props.load(in);
            in.close();
            logger.debug("The default value configuration file has been read over.");
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("Please check if the file \""+DefaultSettings.topologyDefaultValueConfigFile+"\" exists!");
            System.exit(2);
        }
        this.props = props;
    }

    private void setDefaultValues(String sectionName){
            this.setValue(sectionName, "port", this.props.getProperty("port"));
            this.setValue(sectionName, "username", this.props.getProperty("username"));
            this.setValue(sectionName, "password", this.props.getProperty("password"));

    }

    public void readTopology(){

        logger.debug("Reading the default value configuration file \""+DefaultSettings.topologyDefaultValueConfigFile+"\" ...");
        this.readDefaultValueConfig();
        Pattern cmdOptPattern = Pattern.compile("^cmd\\d+$");
        Wini ini = null;
        try {
            logger.info("Reading the topology file \""+this.topologyFile+"\" ...");
            ini = new Wini(new File(this.topologyFile));
            logger.debug("The topology file \""+this.topologyFile+"\" has been opened.");

            for (String sectionName: ini.keySet()) {
//                System.out.println("["+sectionName+"]");
                Profile.Section section = ini.get(sectionName);
                if (!section.containsKey("ip")){
                    logger.warn("Section \""+sectionName+"\" doesn't contain \"ip\" option, skip this section!");
                    continue;
                }

                this.topologyMap.put(sectionName, new DeviceData());


                List<Integer> cmdNumList = new ArrayList<Integer>();
                Map<Integer, String> intCmdMap = new HashMap<Integer, String>();

                for (String optionKey: section.keySet()) {
//                    System.out.println("\t"+optionKey+"="+section.get(optionKey));
                    if (!cmdOptPattern.matcher(optionKey).find()){
                        // not "cmd123"
                        this.topologyMap.get(sectionName).regularOptionsMap.put(optionKey, section.get(optionKey));
                    }else
                    {
                        // is "cmd123"
                        int cmdNum = Integer.parseInt(optionKey.replace("cmd", ""));
                        intCmdMap.put(cmdNum, section.get(optionKey));
                        cmdNumList.add(cmdNum);
                    }
                }

                // Set default value for the option not given in the config file
                this.setDefaultValues(sectionName);
                for (int i=0; i<cmdNumList.size(); i++){
                    for (int j=i; j<cmdNumList.size(); j++){
                        if (cmdNumList.get(j) < cmdNumList.get(i)){
                            int tmpNum = cmdNumList.get(i);
                            cmdNumList.set(i, cmdNumList.get(j));
                            cmdNumList.set(j, tmpNum);
                        }

                    }
                }

                for (int index: cmdNumList){
                    this.topologyMap.get(sectionName).commandsList.add(intCmdMap.get(index));
                }

                logger.debug("The regular options of section \""+sectionName+"\" is:\n"+this.topologyMap.get(sectionName).regularOptionsMap);
                logger.debug("The command list for section \""+sectionName+"\" is:");
                for (String cmdStr: this.topologyMap.get(sectionName).commandsList){
                    logger.debug(cmdStr);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Reading the topology file and build the data structure over.");
    }

}
