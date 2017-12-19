package com.github.paulomigalmeida.downloadfilefromtarins3.upload.parser;

import com.github.paulomigalmeida.downloadfilefromtarins3.base.parser.AbstractBaseProgramArguments;

import java.io.File;

public class UploadProgramArgumentsSingleton extends AbstractBaseProgramArguments {

    private File file;

    private static UploadProgramArgumentsSingleton instance;

    private UploadProgramArgumentsSingleton() {
    }

    public static UploadProgramArgumentsSingleton getInstance() {
        if (instance == null)
            instance = new UploadProgramArgumentsSingleton();
        return instance;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "UploadProgramArgumentsSingleton{" +
                "file=" + file +
                "} " + super.toString();
    }
}
