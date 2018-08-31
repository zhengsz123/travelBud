package com.travel.core.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.travel.core.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(basePackages = "com.travel.core", excludeFilters = @ComponentScan.Filter(type=FilterType.REGEX,pattern="com.travel.core.api.*"))
public class ServiceConfig {

    @Bean
    public StorageService s3Config(@Autowired @Qualifier( "databaseProperties") PropertiesFactoryBean proper) throws Exception{
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        StorageService appBean = new StorageService(s3Client);
        appBean.setBucket(proper.getObject().getProperty("s3.bucketName"));
        return appBean;
    }
}
