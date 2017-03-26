package TestAssistant;

import org.apache.commons.cli.*;

/**
 * Created by root on 1/3/17.
 */
public class Arguments {
    private String [] args;
    private Options options;
    private String [] argsArray; // remain args
    public ArgDefinition myOptions = new ArgDefinition(); // save all the args and options


    public Arguments(String [] args){
        this.args = args;
        this.options = new Options();
    }

    private void addOptions(){
        this.options.addOption("h", "help", false, "Print help message.");
//        this.options.addOption("d", "directory", true, "Specify the configuration directory.");
    }

    private void printHelp(){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "\n"+
                        "NxAssistanT [directory]"+"\n",
                this.options );
        System.exit(0);
    }

    private void argsLimitation(CommandLine cmd){
        if (cmd.hasOption("h")){
            this.printHelp();
        }

//        if (this.argsArray.length < 1){
//            this.printHelp();
//        }


    }

    private void saveArgs(){
        if (this.argsArray.length >= 1){
            this.myOptions.directory = this.argsArray[0];
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
            this.saveArgs();



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
