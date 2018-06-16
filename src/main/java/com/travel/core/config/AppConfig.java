package com.travel.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;


@Configuration
@ComponentScan(basePackages = "com.travel.core")
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean("databaseProperties")
    public PropertiesFactoryBean dbProperties() throws Exception {
        String profile = env.getActiveProfiles()[0];
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("META-INF/"+profile+"-db.properties"));
        return bean;
    }

//    @Bean("mailProperties")
//    public PropertiesFactoryBean mailProperties() throws Exception {
//        String profile = env.getActiveProfiles()[0];
//        PropertiesFactoryBean bean = new PropertiesFactoryBean();
//        bean.setLocation(new ClassPathResource("META-INF/"+profile+"-mail.properties"));
//        return bean;
//    }


}