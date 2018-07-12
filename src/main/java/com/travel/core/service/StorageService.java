package com.travel.core.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.stereotype.Service;

import java.io.File;


public class StorageService {
    private AmazonS3 s3;
    private String bucket;
    public StorageService(AmazonS3 s3){ this.s3=s3;}

    public void putObject(String s3Key, File file){
        s3.putObject(bucket, s3Key, file);
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
