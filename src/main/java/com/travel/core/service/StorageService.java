package com.travel.core.service;

import com.amazonaws.services.s3.AmazonS3;
import com.travel.core.domain.Authority;
import com.travel.core.domain.Media;
import com.travel.core.domain.User;
import com.travel.core.extend.security.Utils;
import com.travel.core.repository.AuthorityRepository;
import com.travel.core.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

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

    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private UserService userService;
    public void putObjectApiImplementation(MultipartFile image) {
        if (!image.isEmpty()) {
            try {
                fileName = image.getOriginalFilename();
                fileNameList = fileName.split("\\.");
                digest = uuid.randomUUID().toString();
                s3Key = fileNameList[0] + "_" + digest +"." +fileNameList[1];
                File file = Utils.multipartToFile(image, fileName);
                putObject(s3Key, file);
                Media media = new Media();
                media.setS3Key(s3Key);
                media.setUrl(getBucket());
                mediaRepository.save(media);
            } catch (IOException i){
                System.err.println(i);
            }
        }
    }
}
