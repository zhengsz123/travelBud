package com.travelbud.apicoreboot.config;

import com.travel.core.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Import(ServiceConfig.class)
@ComponentScan(basePackages = "com.travelbud.apicoreboot")
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
}
