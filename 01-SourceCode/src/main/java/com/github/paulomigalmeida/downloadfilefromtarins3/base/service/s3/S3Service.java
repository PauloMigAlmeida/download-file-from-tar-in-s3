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

package com.github.paulomigalmeida.downloadfilefromtarins3.base.service.s3;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class S3Service {
    // S3 Client
    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();

    public void uploadFile(String bucketName, String keyName, File file) {
        s3.putObject(bucketName, keyName, file);
    }

    public byte[] downloadFile(String bucketName, String keyName) throws IOException {
        S3Object o = s3.getObject(bucketName, keyName);
        S3ObjectInputStream s3is = o.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(s3is);
        s3is.close();
        return bytes;
    }

    public void downloadFile(String bucketName, String keyName, File outputFile, long startPosition, long endPosition) throws IOException {
        GetObjectRequest rangeObjectRequest = new GetObjectRequest(
                bucketName, keyName);
        rangeObjectRequest.setRange(startPosition, endPosition);
        S3Object objectPortion = s3.getObject(rangeObjectRequest);
        S3ObjectInputStream s3is = objectPortion.getObjectContent();

        outputFile.mkdirs();

        Files.copy(s3is, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        s3is.close();
    }
}
