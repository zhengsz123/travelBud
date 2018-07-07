package com.travel.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
@Configuration
@ComponentScan(basePackages = "com.travel.core", excludeFilters = @ComponentScan.Filter(type=FilterType.REGEX,pattern="com.travel.core.api.*"))
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean("databaseProperties")
    public PropertiesFactoryBean dbProperties() throws Exception {
            String profile = env.getActiveProfiles()[0];
            PropertiesFactoryBean bean = new PropertiesFactoryBean();
            bean.setLocation(new ClassPathResource("META-INF/" + profile + "-db.properties"));
            return bean;
    }

    @Bean("shareProperties")
    public PropertiesFactoryBean sharedProperties() throws Exception{
            PropertiesFactoryBean sharedBean = new PropertiesFactoryBean();
            sharedBean.setLocation(new ClassPathResource("META-INF/shared.properties"));
            return sharedBean;
        }


}