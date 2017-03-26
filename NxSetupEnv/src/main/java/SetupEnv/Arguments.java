package SetupEnv;

import org.apache.commons.cli.*;

/**
 * Created by root on 1/8/17.
 */
public class Arguments {

    private String [] args;
    private Options options;
    private String [] argsArray; // remain args
    private String appName;
    public ArgDefinition myOptions = new ArgDefinition(); // save all the args and options


    public Arguments(String [] args, String appName){
        this.args = args;
        this.appName = appName;
        this.options = new Options();
    }

    private void addOptions(){
        this.options.addOption("h", "help", false, "Print help message.");
//        this.options.addOption("d", "directory", true, "Specify the configuration directory.");
        this.options.addOption("d", "debug", false, "Enable debug mode.");
        this.options.addOption("l", "log-directory", true, "Specify the app log file directory. No log file output if not given.");
        this.options.addOption("e", "gen-topology-example", false, "Show topology file example, if this option is enabled, the topology option is not required then.");
        this.options.addOption("f", "shell-commands-file", true, "Generate command sets with prefix \"cmdXX\" from a shell commands file.");
    }

    private void printHelp(){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "\n"+this.appName+
                        " [Topology] [options]"+"\n",
                this.options );
        System.exit(0);
    }

    private void argsLimitation(CommandLine cmd){
        if (cmd.hasOption("h")){
            this.printHelp();
        }

        if (this.argsArray.length < 1 && !cmd.hasOption("e") && !cmd.hasOption("f")){
            this.printHelp();
        }
    }

    private void saveArgs(CommandLine cmd){
        if (this.argsArray.length >= 1){
            this.myOptions.topology = this.argsArray[0];
        }
        if (cmd.hasOption("d")){
            this.myOptions.debug = true;
        }else {
            this.myOptions.debug = false;
        }

        if (cmd.getOptionValue("l") != null){
            this.myOptions.logDirectory = cmd.getOptionValue("l");
        }

        if (cmd.hasOption("e")){
            this.myOptions.genTopologyExample=true;
        }

        if (cmd.getOptionValue("f") != null){
            this.myOptions.shellCommandsFile = cmd.getOptionValue("f");
        }

    }

    public void handle(){
        this.addOptions();
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(this.options, this.args);
            this.argsArray = cmd.getArgs();
            this.argsLimitation(cmd);
//            String dire = cmd.getOptionValue("d");
            this.saveArgs(cmd);

        } catch (ParseException e) {
//            e.printStackTrace();
            System.out.println("");
            System.out.println("There are unexpected arguments, please check again:");
//            e.printStackTrace();
            System.out.print(e.getCause());
            System.out.println("");
            System.exit(2);
        }


    }
}
