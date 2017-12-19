package com.github.paulomigalmeida.downloadfilefromtarins3.base.parser;

public abstract class AbstractBaseProgramArguments {

    private String bucketName;
    private String keyName;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    @Override
    public String toString() {
        return "AbstractBaseProgramArguments{" +
                "bucketName='" + bucketName + '\'' +
                ", keyName='" + keyName + '\'' +
                '}';
    }
}
