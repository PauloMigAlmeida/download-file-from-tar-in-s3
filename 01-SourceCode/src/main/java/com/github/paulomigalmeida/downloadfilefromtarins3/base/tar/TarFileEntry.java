package com.github.paulomigalmeida.downloadfilefromtarins3.base.tar;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;

public class TarFileEntry {

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

    private String name;
    private String mode;
    private String uid;
    private String gid;
    private String size;
    private String mtime;
    private String chksum;
    private String typeFlag;
    private String linkName;
    private String magic;
    private String version;
    private String uname;
    private String gname;
    private String devMajor;
    private String devMinor;
    private String prefix;
    private long fileStartPosition;
    private long fileEndPosition;

    public TarFileEntry(byte[] bytes, long fileStartPosition){
        name     = new String(Arrays.copyOfRange(bytes, 0,   100)).trim();
        mode     = new String(Arrays.copyOfRange(bytes, 100, 108)).trim();
        uid      = new String(Arrays.copyOfRange(bytes, 108, 116)).trim();
        gid      = new String(Arrays.copyOfRange(bytes, 116, 124)).trim();
        size     = new String(Arrays.copyOfRange(bytes, 124, 136)).trim();
        mtime    = new String(Arrays.copyOfRange(bytes, 136, 148)).trim();
        chksum   = new String(Arrays.copyOfRange(bytes, 148, 156)).trim();
        typeFlag = new String(Arrays.copyOfRange(bytes, 156, 157)).trim();
        linkName = new String(Arrays.copyOfRange(bytes, 157, 257)).trim();
        magic    = new String(Arrays.copyOfRange(bytes, 257, 263)).trim();
        version  = new String(Arrays.copyOfRange(bytes, 263, 265)).trim();
        uname    = new String(Arrays.copyOfRange(bytes, 265, 297)).trim();
        gname    = new String(Arrays.copyOfRange(bytes, 297, 329)).trim();
        devMajor = new String(Arrays.copyOfRange(bytes, 329, 337)).trim();
        devMinor = new String(Arrays.copyOfRange(bytes, 337, 345)).trim();
        prefix   = new String(Arrays.copyOfRange(bytes, 345, 500)).trim();
        this.fileStartPosition = fileStartPosition;
        this.fileEndPosition = fileStartPosition + Long.parseLong(size,8);
    }

    public String getName() {
        return name;
    }

    public String getMode() {
        return mode;
    }

    public String getUid() {
        return uid;
    }

    public String getGid() {
        return gid;
    }

    public String getSize() {
        return size;
    }

    @JsonIgnore
    public long getSizeInDecimal(){
        return Long.parseLong(size, 8);
    }

    public String getMtime() {
        return mtime;
    }

    public String getChksum() {
        return chksum;
    }

    public String getTypeFlag() {
        return typeFlag;
    }

    public String getLinkName() {
        return linkName;
    }

    public String getMagic() {
        return magic;
    }

    public String getVersion() {
        return version;
    }

    public String getUname() {
        return uname;
    }

    public String getGname() {
        return gname;
    }

    public String getDevMajor() {
        return devMajor;
    }

    public String getDevMinor() {
        return devMinor;
    }

    public String getPrefix() {
        return prefix;
    }

    public long getFileStartPosition() {
        return fileStartPosition;
    }

    public long getFileEndPosition() {
        return fileEndPosition;
    }

    @Override
    public String toString() {
        return "TarFileEntry{" +
                "name='" + name + '\'' +
                ", mode='" + mode + '\'' +
                ", uid='" + uid + '\'' +
                ", gid='" + gid + '\'' +
                ", size='" + size + '\'' +
                ", mtime='" + mtime + '\'' +
                ", chksum='" + chksum + '\'' +
                ", typeFlag='" + typeFlag + '\'' +
                ", linkName='" + linkName + '\'' +
                ", magic='" + magic + '\'' +
                ", version='" + version + '\'' +
                ", uname='" + uname + '\'' +
                ", gname='" + gname + '\'' +
                ", devMajor='" + devMajor + '\'' +
                ", devMinor='" + devMinor + '\'' +
                ", prefix='" + prefix + '\'' +
                ", fileStartPosition=" + fileStartPosition +
                ", fileEndPosition=" + fileEndPosition +
                '}';
    }
}
