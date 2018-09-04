package com.travel.mvc.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.travel.core.config.ServiceConfig;
import com.travel.core.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Import(ServiceConfig.class)
@ComponentScan(basePackages = "com.travel.mvc", excludeFilters = @ComponentScan.Filter(type=FilterType.REGEX,pattern="com.travel.core.api.*"))
public class AppConfig {

    @Autowired
    private Environment env;


    @Bean("databaseProperties")
    public PropertiesFactoryBean dbProperties() throws Exception {
            String profile = env.getActiveProfiles()[0];
            PropertiesFactoryBean bean = new PropertiesFactoryBean();
            bean.setLocation(new ClassPathResource("env/" + profile + "-db.properties"));
            return bean;
    }

    @Bean("sharedProperties")
    public PropertiesFactoryBean sharedProperties() throws Exception{
            PropertiesFactoryBean sharedBean = new PropertiesFactoryBean();
            sharedBean.setLocation(new ClassPathResource("env/shared.properties"));
            return sharedBean;
        }


    @Bean
    public StorageService s3Config(@Autowired @Qualifier( "databaseProperties") PropertiesFactoryBean proper) throws Exception{

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        StorageService appBean = new StorageService(s3Client);
        appBean.setBucket(proper.getObject().getProperty("s3.bucketName"));
        return appBean;
    }


}