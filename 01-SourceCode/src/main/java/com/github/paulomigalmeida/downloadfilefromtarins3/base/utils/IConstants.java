package com.github.paulomigalmeida.downloadfilefromtarins3.base.utils;

public interface IConstants {

    String BASE_CMD_LINE_SYNTAX = "java -jar download-file-from-tar-in-s3.jar";
    String DEFAULT_CMD_LINE_SYNTAX = String.format("%s <options>", BASE_CMD_LINE_SYNTAX);
    String UPLOAD_CMD_LINE_SYNTAX = String.format("%s upload <options>", BASE_CMD_LINE_SYNTAX);
    String DOWNLOAD_CMD_LINE_SYNTAX = String.format("%s download <options>", BASE_CMD_LINE_SYNTAX);

}
