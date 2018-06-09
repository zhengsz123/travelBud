package com.travel.core.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.activation.DataSource;

@Configuration
public class PersistenceContext {
//    @Bean(destroyMethod = "close")
//    DataSource dataSource(Environment env) {
//        HikariConfig dataSourceConfig = new HikariConfig();
//        dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
//        dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
//        dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));
//        dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));
//
//        return new HikariDataSource(dataSourceConfig);
//    }
}
