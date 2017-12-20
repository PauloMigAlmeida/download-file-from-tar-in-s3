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

package com.github.paulomigalmeida.downloadfilefromtarins3.base.parser;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBaseParser {

    protected Options options;

    protected abstract void parse(String[] args);

    public void run(String[] args){
        // Remove operation parameter since it's a logical parameter only
        List<String> newArgs = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if(args[i].equals("-operation")){
                i++;
            }else{
                newArgs.add(args[i]);
            }
        }
        parse(newArgs.toArray(new String[0]));
    }

    protected void showUsage(String cmdLineSyntax){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(cmdLineSyntax,options);
    }

    public Options getOptions() {
        return options;
    }
}
