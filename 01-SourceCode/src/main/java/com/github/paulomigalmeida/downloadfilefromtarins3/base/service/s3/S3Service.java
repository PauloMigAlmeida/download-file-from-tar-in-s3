package com.github.paulomigalmeida.downloadfilefromtarins3.base.service.s3;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.File;

public class S3Service {

    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();

    public void uploadFile(String bucketName, String keyName, File file){
        s3.putObject(bucketName, keyName, file);
    }
}
