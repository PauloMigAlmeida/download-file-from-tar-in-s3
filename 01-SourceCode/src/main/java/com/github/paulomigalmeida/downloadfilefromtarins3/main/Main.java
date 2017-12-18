package com.github.paulomigalmeida.downloadfilefromtarins3.main;

import com.github.paulomigalmeida.downloadfilefromtarins3.upload.parser.UploadProgramParser;
import org.apache.commons.cli.*;

public class Main {

    static class MainParser{

        private Options options;

        MainParser(){
            Option help = new Option("help", "print this message");
            Option operation = Option.builder("operation")
                    .required()
                    .hasArg()
                    .desc("Operation - It needs to be either upload or download")
                    .numberOfArgs(1).build();

            options = new Options();
            options.addOption(help);
            options.addOption(operation);
        }

        String run(String[] args){
            // create the parser
            CommandLineParser parser = new DefaultParser();
            try {
                // parse the command line arguments
                CommandLine line = parser.parse(options, args);
                return line.getOptionValue("operation");
            } catch (ParseException exp) {
                System.err.println("Parsing failed.  Reason: " + exp.getMessage());
                showUsage();
                System.exit(1);
                return null;
            }
        }

        void showUsage(){
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar download-file-from-tar-in-s3.jar options", options);
            System.out.println("\n");
            formatter.printHelp("java -jar download-file-from-tar-in-s3.jar upload options", new UploadProgramParser().getOptions());
        }
    }

    public static void main(String[] args){
        MainParser parser = new MainParser();

        // Options parse
        switch (parser.run(args)) {
            case "upload":
                break;
            case "download":
                break;
            default:
                System.err.println("Error while parsing arguments: Invalid operation");
                parser.showUsage();
                System.exit(1);
        }
    }

}
