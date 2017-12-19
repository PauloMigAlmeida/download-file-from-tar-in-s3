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

package com.github.paulomigalmeida.downloadfilefromtarins3.main;

import com.github.paulomigalmeida.downloadfilefromtarins3.base.parser.AbstractBaseParser;
import com.github.paulomigalmeida.downloadfilefromtarins3.download.controller.DownloadFlowController;
import com.github.paulomigalmeida.downloadfilefromtarins3.download.parser.DownloadProgramParser;
import com.github.paulomigalmeida.downloadfilefromtarins3.upload.controller.UploadFlowController;
import com.github.paulomigalmeida.downloadfilefromtarins3.upload.parser.UploadProgramParser;
import org.apache.commons.cli.*;

import static com.github.paulomigalmeida.downloadfilefromtarins3.base.utils.IConstants.*;

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
            System.out.println("\n");
            formatter.printHelp(DOWNLOAD_CMD_LINE_SYNTAX, new DownloadProgramParser().getOptions());
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
                UploadFlowController uploadFlowController = new UploadFlowController();
                uploadFlowController.execute();
                break;
            case "download":
                // Parse download arguments
                baseParser = new DownloadProgramParser();
                baseParser.run(args);

                // Initiate the Download flow
                DownloadFlowController downloadFlowController = new DownloadFlowController();
                downloadFlowController.execute();
                break;
            default:
                System.err.println("Error while parsing arguments: Invalid operation");
                parser.showUsage();
                System.exit(1);
        }
    }

}
