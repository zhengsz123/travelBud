package com.travelbud.apicoreboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiCoreBootApplication  {

    public static void main(String[] args) {
        SpringApplication.run(ApiCoreBootApplication.class, args);
    }
}
