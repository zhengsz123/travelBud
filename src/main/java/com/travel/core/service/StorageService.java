package com.travel.core.service;

import com.amazonaws.services.s3.AmazonS3;
import com.travel.core.extend.security.Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static com.vividsolutions.jts.io.WKBWriter.bytesToHex;


public class StorageService {
    private AmazonS3 s3;
    private String bucket;
    private String fileName;
    private String s3Key;
    private String []fileNameList=new String[10];
    private UUID uuid;
    private String digest;
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

    public void putObjectApiImplementation(MultipartFile image) {
        if (!image.isEmpty()) {
            try {
                fileName = image.getOriginalFilename();
                fileNameList = fileName.split("\\.");

                digest = uuid.randomUUID().toString();
                s3Key = fileNameList[0] + "_" + digest + "."+fileNameList[1];
                File file = Utils.multipartToFile(image, fileName);
                putObject(s3Key, file);
            }
            catch (IOException i){
                System.err.println(i);
            }

        }
    }
}
