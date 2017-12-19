package com.github.paulomigalmeida.downloadfilefromtarins3.download.parser;

import com.github.paulomigalmeida.downloadfilefromtarins3.base.parser.AbstractBaseParser;
import org.apache.commons.cli.*;

import java.io.File;

import static com.github.paulomigalmeida.downloadfilefromtarins3.base.utils.IConstants.UPLOAD_CMD_LINE_SYNTAX;

public class DownloadProgramParser extends AbstractBaseParser {

    public DownloadProgramParser() {
        Option operation = Option.builder("operation")
                .required()
                .hasArg()
                .desc("Operation - It needs to be either upload or download")
                .numberOfArgs(1).build();

        Option bucketName = Option.builder("aws_bucket_name")
                .required()
                .hasArg()
                .desc("S3 bucket name")
                .numberOfArgs(1).build();

        Option keyName = Option.builder("aws_key_name")
                .required()
                .hasArg()
                .desc("S3 bucket name")
                .numberOfArgs(1).build();

        Option tarFileEntry = Option.builder("tar_file_entry")
                .required()
                .hasArg()
                .desc("entry within the tar file")
                .numberOfArgs(1).build();

        Option output = Option.builder("output")
                .required()
                .hasArg()
                .desc("destination file")
                .numberOfArgs(1).build();

        options = new Options();
        options.addOption(operation);
        options.addOption(bucketName);
        options.addOption(keyName);
        options.addOption(tarFileEntry);
        options.addOption(output);
    }

    public void run(String[] args) {
        // create the parser
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args, true);
            DownloadProgramArgumentsSingleton.getInstance().setBucketName(line.getOptionValue("aws_bucket_name"));
            DownloadProgramArgumentsSingleton.getInstance().setKeyName(line.getOptionValue("aws_key_name"));
            DownloadProgramArgumentsSingleton.getInstance().setTarFileEntry(line.getOptionValue("tar_file_entry"));
            DownloadProgramArgumentsSingleton.getInstance().setOutput(new File(line.getOptionValue("output")));
        } catch (ParseException exp) {
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            showUsage(UPLOAD_CMD_LINE_SYNTAX);
            System.exit(1);
        }
    }

}
