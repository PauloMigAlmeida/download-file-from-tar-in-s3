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

import com.github.paulomigalmeida.downloadfilefromtarins3.base.parser.AbstractBaseProgramArguments;

import java.io.File;

public class DownloadProgramArgumentsSingleton extends AbstractBaseProgramArguments {

    private String tarFileEntry;
    private File output;

    private static DownloadProgramArgumentsSingleton instance;

    private DownloadProgramArgumentsSingleton() {
    }

    public static DownloadProgramArgumentsSingleton getInstance() {
        if (instance == null)
            instance = new DownloadProgramArgumentsSingleton();
        return instance;
    }

    public String getTarFileEntry() {
        return tarFileEntry;
    }

    public void setTarFileEntry(String tarFileEntry) {
        this.tarFileEntry = tarFileEntry;
    }

    public File getOutput() {
        return output;
    }

    public void setOutput(File output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "DownloadProgramArgumentsSingleton{" +
                "tarFileEntry='" + tarFileEntry + '\'' +
                ", output=" + output +
                "} " + super.toString();
    }
}
