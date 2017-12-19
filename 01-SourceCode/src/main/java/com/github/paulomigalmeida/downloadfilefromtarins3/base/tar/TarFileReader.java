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
