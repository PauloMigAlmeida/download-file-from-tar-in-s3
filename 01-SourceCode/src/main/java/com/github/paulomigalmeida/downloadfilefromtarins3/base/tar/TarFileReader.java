package com.github.paulomigalmeida.downloadfilefromtarins3.base.tar;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TarFileReader {

    private static int TAR_POSIX_HEADER_LENGTH = 512;
    private static int TAR_DEFAULT_BLOCK_SIZE = 512;
    private static int TAR_TERMINATION_BLOCK = TAR_DEFAULT_BLOCK_SIZE * 2;

    private File file;

    public TarFileReader(File file) {
        this.file = file;
    }

    public List<TarFileEntry> listFiles() throws IOException {
        List<TarFileEntry> returnList = new ArrayList<>();

        long position = 0;
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        while((position + TAR_POSIX_HEADER_LENGTH) < randomAccessFile.length()){
            randomAccessFile.seek(position);
            byte[] bytes = new byte[TAR_POSIX_HEADER_LENGTH];
            randomAccessFile.read(bytes);

            if((position + TAR_TERMINATION_BLOCK) == randomAccessFile.length()){
                break;
            }

            TarFileEntry tarFileEntry = new TarFileEntry(bytes, (position + TAR_POSIX_HEADER_LENGTH));
            System.out.println(tarFileEntry.toString());
            returnList.add(tarFileEntry);

            position += (long)(Math.ceil((TAR_POSIX_HEADER_LENGTH + tarFileEntry.getSizeInDecimal()) /
                    ((double)TAR_DEFAULT_BLOCK_SIZE)) * TAR_DEFAULT_BLOCK_SIZE);
        }
        return returnList;
    }


}
