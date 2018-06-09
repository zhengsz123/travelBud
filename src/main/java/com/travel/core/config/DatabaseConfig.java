package com.travel.core.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.travel.core.repository")
public class DatabaseConfig {

    @Bean
    public  DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.ds.PGSimpleDataSource");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/travelBud");
        dataSource.setUsername("admin");
        dataSource.setPassword("password");
//        dataSource.setValidationQuery(databaseValidationQuery);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(3);
        dataSource.setMinEvictableIdleTimeMillis(1800000);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManager(){
        LocalContainerEntityManagerFactoryBean factoryBean = setUpLocalContainerEntityManagerFactoryBean();
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.spatial.dialect.postgis.PostgisDialect");
        props.put("hibernate.hbm2ddl.auto", "validate");
//        props.put("hibernate.physical_naming_strategy", "io.ascending.training.extend.hibernate.ImprovedNamingStrategy");
        props.put("hibernate.connection.charSet","UTF-8");
        props.put("hibernate.show_sql","true");
//        props.put("org.hibernate.flushMode","ALWAYS");
//            <property name="hibernate.ejb.interceptor" value="com.overture.family.repository.jpa.DBNullsFirstLastInteceptor"/>
        factoryBean.setJpaProperties(props);

        return factoryBean;

    }

    private LocalContainerEntityManagerFactoryBean setUpLocalContainerEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan(new String[] { "com.travel.core.domain","com.travel.core.repository" });
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager getTransactionManager(@Autowired EntityManagerFactory factor, @Autowired DataSource dataSource){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factor);
        transactionManager.setDataSource(dataSource);
        return  transactionManager;
    }

}
