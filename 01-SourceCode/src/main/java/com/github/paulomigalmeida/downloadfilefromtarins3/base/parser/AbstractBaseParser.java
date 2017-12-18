package com.github.paulomigalmeida.downloadfilefromtarins3.base.parser;

import org.apache.commons.cli.*;

public abstract class AbstractBaseParser {

    protected Options options;

    public abstract void run(String[] args);

    public void showUsage(){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("",options);
    }

    public Options getOptions() {
        return options;
    }
}
