package com.travel.core.config;

import com.travel.core.email.RegistrationEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.util.Properties;

@Configuration
public class EmailConfig {
    @Profile({"dev", "test", "prod"})
    @Bean(name = "mailSender")
    public JavaMailSenderImpl getEmailSender() {
        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        emailSender.setHost("smtp.sendgrid.net");
        emailSender.setPort(465);
        emailSender.setProtocol("smtps");
        emailSender.setUsername("zhengsz");
        emailSender.setPassword("19941227t");
        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.smtps.auth", "true");
        mailProperties.setProperty("mail.smtp.ssl.enable", "true");
        mailProperties.setProperty("mail.transport.protocol", "smtps");
        emailSender.setJavaMailProperties(mailProperties);
        return emailSender;
    }

    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerMailConfiguration(){
        FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
        freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("classpath:/WEB-INF/mail/");
        freeMarkerConfigurationFactoryBean.setPreferFileSystemAccess(false);
        return freeMarkerConfigurationFactoryBean;
    }

    @Bean
    public RegistrationEmail getRegistrationConfiguration(@Autowired JavaMailSenderImpl sender,@Autowired freemarker.template.Configuration configuration){
        RegistrationEmail registrationEmail = new RegistrationEmail();
        registrationEmail.setMailSender(sender);
        registrationEmail.setConfiguration(configuration);
        registrationEmail.setFreemarkerTemplate("RegistrationEmail.ftl");
        return registrationEmail;
    }
}
