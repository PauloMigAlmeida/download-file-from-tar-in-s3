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
