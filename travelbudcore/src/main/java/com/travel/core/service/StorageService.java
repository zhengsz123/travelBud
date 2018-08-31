package com.travel.core.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.travel.core.Utils;
import com.travel.core.domain.Media;
import com.travel.core.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class StorageService {
    @Value("#{databaseProperties['s3.url']}")
    private String s3Url;
    private AmazonS3 s3;
    private String bucket;
    private String fileName;
    private String s3Key;
    private String []fileNameList=new String[10];
    private UUID uuid;
    private String digest;
    private String url;
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
                url=s3Url+getBucket()+"/"+s3Key;
                media.setUrl(url);
                mediaRepository.save(media);
            } catch (IOException i){
                System.err.println(i);
            }
        }
    }

    @Transactional(readOnly = true)
    public Media getImageInfo(Long id)
    {
        Optional<Media>optional = mediaRepository.findKeyandUrlById(id);
        Media obj = optional.get();
        return obj;
    }

    @Transactional(readOnly = true)
    public Media downloadImage(Long id){
        Optional<Media>optional = mediaRepository.findKeyandUrlById(id);
        Media obj = optional.get();
        String s3Key = obj.getS3Key();
        try {
            S3Object s3Object = s3.getObject(getBucket(), s3Key);
            S3ObjectInputStream s3Input = s3Object.getObjectContent();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(s3Key));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3Input.read(read_buf)) > 0) {
                fileOutputStream.write(read_buf, 0, read_len);
            }
            s3Input.close();
            fileOutputStream.close();
        }
        catch (FileNotFoundException f){

        }
        catch (IOException i){

        }
        return obj;
    }

}
