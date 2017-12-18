package com.github.paulomigalmeida.downloadfilefromtarins3.upload.parser;

public class UploadProgramArgumentsSingleton {
    private String bucketName;
    private String keyName;

    private static UploadProgramArgumentsSingleton instance;

    private UploadProgramArgumentsSingleton(){}

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

    public static UploadProgramArgumentsSingleton getInstance() {
        if(instance == null)
            instance = new UploadProgramArgumentsSingleton();
        return instance;
    }

    @Override
    public String toString() {
        return "UploadProgramArgumentsSingleton{" +
                "bucketName='" + bucketName + '\'' +
                ", keyName='" + keyName + '\'' +
                '}';
    }

}
