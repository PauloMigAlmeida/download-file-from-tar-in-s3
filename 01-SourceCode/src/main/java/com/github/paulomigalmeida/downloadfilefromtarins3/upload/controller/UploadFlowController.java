package com.github.paulomigalmeida.downloadfilefromtarins3.upload.controller;

import com.github.paulomigalmeida.downloadfilefromtarins3.base.controller.AbstractController;
import com.github.paulomigalmeida.downloadfilefromtarins3.base.tar.TarFileReader;
import com.github.paulomigalmeida.downloadfilefromtarins3.upload.parser.UploadProgramArgumentsSingleton;

public class UploadFlowController extends AbstractController<Void> {

    @Override
    public Void execute() throws Exception {

        TarFileReader tarFileReader = new TarFileReader(UploadProgramArgumentsSingleton.getInstance().getFile());
        tarFileReader.listFiles();

        return null;
    }

}
