package com.github.paulomigalmeida.downloadfilefromtarins3.base.tar;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class TarFileReader {

    private static int TAR_POSIX_HEADER_LENGTH = 512;
    private static int TAR_TERMINATION_BLOCK = 1024;

    private File file;

    public TarFileReader(File file) {
        this.file = file;
    }

    public void listFiles() throws IOException {
//        /* tar Header Block, from POSIX 1003.1-1990.  */
//
//        /* POSIX header.  */
//
//        struct posix_header
//        {                              /* byte offset */
//            char name[100];               /*   0 */
//            char mode[8];                 /* 100 */
//            char uid[8];                  /* 108 */
//            char gid[8];                  /* 116 */
//            char size[12];                /* 124 */
//            char mtime[12];               /* 136 */
//            char chksum[8];               /* 148 */
//            char typeflag;                /* 156 */
//            char linkname[100];           /* 157 */
//            char magic[6];                /* 257 */
//            char version[2];              /* 263 */
//            char uname[32];               /* 265 */
//            char gname[32];               /* 297 */
//            char devmajor[8];             /* 329 */
//            char devminor[8];             /* 337 */
//            char prefix[155];             /* 345 */
//                                          /* 500 */
//        };
        int position = 0;
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        while((position + TAR_POSIX_HEADER_LENGTH) < randomAccessFile.length()){
            randomAccessFile.seek(position);
            byte[] bytes = new byte[TAR_POSIX_HEADER_LENGTH];
            randomAccessFile.read(bytes);

            if((position + TAR_TERMINATION_BLOCK) == randomAccessFile.length()){
                break;
            }


            String name = new String(Arrays.copyOfRange(bytes, 0, 100)).trim();
            String mode = new String(Arrays.copyOfRange(bytes, 100, 108)).trim();
            String uid = new String(Arrays.copyOfRange(bytes, 108, 116)).trim();
            String gid = new String(Arrays.copyOfRange(bytes, 116, 124)).trim();
            String size = new String(Arrays.copyOfRange(bytes, 124, 136)).trim();
            String mtime = new String(Arrays.copyOfRange(bytes, 136, 148)).trim();
            String chksum = new String(Arrays.copyOfRange(bytes, 148, 156)).trim();
            String typeflag = new String(Arrays.copyOfRange(bytes, 156, 157)).trim();
            String linkname = new String(Arrays.copyOfRange(bytes, 157, 257)).trim();
            String magic = new String(Arrays.copyOfRange(bytes, 257, 263)).trim();
            String version = new String(Arrays.copyOfRange(bytes, 263, 265)).trim();
            String uname = new String(Arrays.copyOfRange(bytes, 265, 297)).trim();
            String gname = new String(Arrays.copyOfRange(bytes, 297, 329)).trim();
            String devmajor = new String(Arrays.copyOfRange(bytes, 329, 337)).trim();
            String devminor = new String(Arrays.copyOfRange(bytes, 337, 345)).trim();
            String prefix = new String(Arrays.copyOfRange(bytes, 345, 500)).trim();
            System.out.println("==========================================================");

            System.out.println(name);
            System.out.println(mode);
            System.out.println(uid);
            System.out.println(gid);
            System.out.println(size);
            System.out.println(mtime);
            System.out.println(chksum);
            System.out.println(typeflag);
            System.out.println(linkname);
            System.out.println(magic);
            System.out.println(version);
            System.out.println(uname);
            System.out.println(gname);
            System.out.println(devmajor);
            System.out.println(devminor);
            System.out.println(prefix);

            position += (long)(Math.ceil((TAR_POSIX_HEADER_LENGTH + Integer.parseInt(size,8)) / 512.0) * 512);
        }

    }


}
