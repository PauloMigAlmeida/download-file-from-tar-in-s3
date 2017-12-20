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

package com.github.paulomigalmeida.downloadfilefromtarins3.base.utils;

public interface IConstants {

    // CommonsCLI - Command Parser
    String BASE_CMD_LINE_SYNTAX = "java -jar download-file-from-tar-in-s3.jar";
    String DEFAULT_CMD_LINE_SYNTAX = String.format("%s <options>", BASE_CMD_LINE_SYNTAX);
    String UPLOAD_CMD_LINE_SYNTAX = String.format("%s -operation upload <options>", BASE_CMD_LINE_SYNTAX);
    String DOWNLOAD_CMD_LINE_SYNTAX = String.format("%s -operation download <options>", BASE_CMD_LINE_SYNTAX);

    // Tar file
    String TAR_METADATA_FILE_SUFFIX = ".metadata";
}
