package com.travel.core.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
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
    @Value("#{databaseProperties['db.username']}")
    private String userName;
    @Value("#{databaseProperties['db.password']}")
    private String passWord;
    @Value("#{databaseProperties['db.url']}")
    private String url;
    @Value("#{databaseProperties['db.driver']}")
    private String driver;
    @Value("#{databaseProperties['db.port']}")
    private String port;
    @Value("#{databaseProperties['db.dName']}")
    private String dName;
    @Value("#{databaseProperties['db.serverName']}")
    private String serverName;
    @Value("#{databaseProperties['db.jdbcPostgresql']}")
    private  String jdbc;


    @Bean
    public  DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(jdbc+"://"+url+":"+port+"/"+dName);
        dataSource.setUsername(userName);
        dataSource.setPassword(passWord);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(3);
        dataSource.setMinEvictableIdleTimeMillis(1800000);

        return dataSource;
    }

    @Profile({"dev","unit"})
    @Bean(name = "flyway",initMethod = "validate")
    public Flyway flywayDev(@Autowired DataSource dataSource){
       return setUpFlyWay(dataSource);
    }

    @Profile({"stage","prod","test"})
    @Bean(name = "flyway",initMethod = "migrate")
    public Flyway flywayDevMigrate(@Autowired DataSource dataSource){
        return setUpFlyWay(dataSource);
    }

    @Bean(name = "entityManagerFactory")
    @DependsOn("flyway")
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

    @Bean(name="transactionManager")
    public PlatformTransactionManager getTransactionManager(@Autowired EntityManagerFactory factor, @Autowired DataSource dataSource){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factor);
        transactionManager.setDataSource(dataSource);
        return  transactionManager;
    }

    private Flyway setUpFlyWay(DataSource dataSource) {
       Flyway flyway = new Flyway();
       flyway.setBaselineOnMigrate(true);
       flyway.setLocations("classpath:db/migration/");
       flyway.setDataSource(dataSource);
       return flyway;
   }

}
