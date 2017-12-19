package com.github.paulomigalmeida.downloadfilefromtarins3.upload.operation;

import com.amazonaws.jmespath.ObjectMapperSingleton;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.paulomigalmeida.downloadfilefromtarins3.base.operation.IOperation;
import com.github.paulomigalmeida.downloadfilefromtarins3.base.tar.TarFileEntry;
import com.github.paulomigalmeida.downloadfilefromtarins3.base.tar.TarFileReader;
import com.github.paulomigalmeida.downloadfilefromtarins3.upload.parser.UploadProgramArgumentsSingleton;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import static com.github.paulomigalmeida.downloadfilefromtarins3.base.utils.IConstants.TAR_METADATA_FILE_SUFFIX;

public class TarMetadataFileGenerator implements IOperation<File> {

    private File tarFile;

    public TarMetadataFileGenerator(File tarFile) {
        this.tarFile = tarFile;
    }

    @Override
    public File performOperation() throws Exception {
        // Reading tar file.
        TarFileReader tarFileReader = new TarFileReader(tarFile);
        List<TarFileEntry> fileEntries = tarFileReader.listFiles();

        // Generating JSON
        ObjectMapper mapper = ObjectMapperSingleton.getObjectMapper();
        ArrayNode messageJsonNode = mapper.createArrayNode();
        for (TarFileEntry fileEntry : fileEntries) {
            messageJsonNode.add(mapper.valueToTree(fileEntry));
        }

        // Writing tar metadata file.
        File outputFile = new File(tarFile.getAbsolutePath() + TAR_METADATA_FILE_SUFFIX);
        FileOutputStream fout = new FileOutputStream(outputFile);
        fout.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(messageJsonNode).getBytes());
        fout.flush();
        fout.close();

        return outputFile;
    }
}
