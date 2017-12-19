package com.github.paulomigalmeida.downloadfilefromtarins3.upload.controller;

import com.github.paulomigalmeida.downloadfilefromtarins3.base.controller.AbstractController;
import com.github.paulomigalmeida.downloadfilefromtarins3.base.service.s3.S3Service;
import com.github.paulomigalmeida.downloadfilefromtarins3.upload.operation.TarMetadataFileGenerator;
import com.github.paulomigalmeida.downloadfilefromtarins3.upload.parser.UploadProgramArgumentsSingleton;

import java.io.File;

import static com.github.paulomigalmeida.downloadfilefromtarins3.base.utils.IConstants.TAR_METADATA_FILE_SUFFIX;

public class UploadFlowController extends AbstractController<Void> {

    @Override
    public Void execute() throws Exception {
        File inputFile = UploadProgramArgumentsSingleton.getInstance().getFile();

        // Generating tar metadata file
        TarMetadataFileGenerator tarMetadataFileGenerator = new TarMetadataFileGenerator(inputFile);
        File metadataFile = tarMetadataFileGenerator.performOperation();

        // Uploading both tar and metadata files
        S3Service s3Service = new S3Service();

        s3Service.uploadFile(
                UploadProgramArgumentsSingleton.getInstance().getBucketName(),
                UploadProgramArgumentsSingleton.getInstance().getKeyName(),
                inputFile
        );
        s3Service.uploadFile(
                UploadProgramArgumentsSingleton.getInstance().getBucketName(),
                UploadProgramArgumentsSingleton.getInstance().getKeyName() + TAR_METADATA_FILE_SUFFIX,
                metadataFile
        );

        return null;
    }

}
