package com.github.paulomigalmeida.downloadfilefromtarins3.upload.parser;

import com.github.paulomigalmeida.downloadfilefromtarins3.base.parser.AbstractBaseParser;
import org.apache.commons.cli.*;

public class UploadProgramParser extends AbstractBaseParser{

    public UploadProgramParser(){
        Option bucketName = Option.builder("bucketName")
                .required()
                .hasArg()
                .desc("S3 bucket name")
                .numberOfArgs(1).build();

        Option keyName = Option.builder("keyName")
                .required()
                .hasArg()
                .desc("S3 bucket name")
                .numberOfArgs(1).build();

        options = new Options();
        options.addOption(bucketName);
        options.addOption(keyName);
    }

    public void run(String[] args){
        // create the parser
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
            UploadProgramArgumentsSingleton.getInstance().setBucketName(line.getOptionValue("bucket_name"));
            UploadProgramArgumentsSingleton.getInstance().setKeyName(line.getOptionValue("key_name"));
        } catch (ParseException exp) {
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            showUsage();
            System.exit(1);
        }
    }

}
