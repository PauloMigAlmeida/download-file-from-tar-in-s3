package com.github.paulomigalmeida.downloadfilefromtarins3.main;

import com.github.paulomigalmeida.downloadfilefromtarins3.base.parser.AbstractBaseParser;
import com.github.paulomigalmeida.downloadfilefromtarins3.upload.controller.UploadFlowController;
import com.github.paulomigalmeida.downloadfilefromtarins3.upload.parser.UploadProgramParser;
import org.apache.commons.cli.*;

import static com.github.paulomigalmeida.downloadfilefromtarins3.base.utils.IConstants.DEFAULT_CMD_LINE_SYNTAX;
import static com.github.paulomigalmeida.downloadfilefromtarins3.base.utils.IConstants.UPLOAD_CMD_LINE_SYNTAX;

public class Main {

    static class MainParser {

        private Options options;

        MainParser() {
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

        String run(String[] args) {
            // create the parser
            CommandLineParser parser = new DefaultParser();
            try {
                // parse the command line arguments
                CommandLine line = parser.parse(options, args, true);
                return line.getOptionValue("operation");
            } catch (ParseException exp) {
                System.err.println("Parsing failed.  Reason: " + exp.getMessage());
                showUsage();
                System.exit(1);
                return null;
            }
        }

        void showUsage() {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(DEFAULT_CMD_LINE_SYNTAX, options);
            System.out.println("\n");
            formatter.printHelp(UPLOAD_CMD_LINE_SYNTAX, new UploadProgramParser().getOptions());
        }
    }

    public static void main(String[] args) throws Exception {
        // Parsers
        MainParser parser = new MainParser();
        AbstractBaseParser baseParser;

        // Base options parse
        switch (parser.run(args)) {
            case "upload":
                // Parse upload arguments
                baseParser = new UploadProgramParser();
                baseParser.run(args);

                // Initiate the Upload flow
                UploadFlowController flowController = new UploadFlowController();
                flowController.execute();
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
