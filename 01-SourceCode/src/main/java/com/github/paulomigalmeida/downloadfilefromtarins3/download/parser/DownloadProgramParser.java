/*
 * The MIT License
 *
 * Copyright (c) 2017-2017 Paulo Miguel Almeida <paulo.miguel.almeida.rodenas(at)gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.paulomigalmeida.downloadfilefromtarins3.download.parser;

import com.github.paulomigalmeida.downloadfilefromtarins3.base.parser.AbstractBaseParser;
import org.apache.commons.cli.*;

import java.io.File;

import static com.github.paulomigalmeida.downloadfilefromtarins3.base.utils.IConstants.UPLOAD_CMD_LINE_SYNTAX;

public class DownloadProgramParser extends AbstractBaseParser {

    public DownloadProgramParser() {
        Option bucketName = Option.builder("aws_bucket_name")
                .required()
                .hasArg()
                .desc("S3 bucket name")
                .numberOfArgs(1).build();

        Option keyName = Option.builder("aws_key_name")
                .required()
                .hasArg()
                .desc("S3 key name")
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
        options.addOption(bucketName);
        options.addOption(keyName);
        options.addOption(tarFileEntry);
        options.addOption(output);
    }

    protected void parse(String[] args) {
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
