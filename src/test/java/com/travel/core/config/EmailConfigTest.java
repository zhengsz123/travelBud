package com.travel.core.config;

import com.travel.core.email.RegistrationEmail;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.util.Properties;

@Configuration
public class EmailConfigTest {

    @Profile({"unit"})
    @Bean(name = "mailSender")
    public JavaMailSenderImpl getEmailSender() {
        JavaMailSenderImpl emailSender = Mockito.mock(JavaMailSenderImpl.class);
        return emailSender;
    }

}
