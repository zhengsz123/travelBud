package com.travel.core.service;

import com.travel.core.config.AppConfig;
import com.travel.core.config.DatabaseConfig;
import com.travel.core.domain.User;
import com.travel.core.email.RegistrationEmail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class,DatabaseConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class EmailServiceTest {
    @Autowired
    private RegistrationEmail registrationEmail;
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Test
    @Transactional
    public void confirmEmailTest(){
        User u = new User();
        MimeMessage mockMsg = Mockito.mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mockMsg);
        Map<String, Serializable> root = new HashMap<>();
        u.setEmail("zhengsz@vt.edu");
        u.setPassword("19941227");
        u.setLastName("zhang");
        u.setFirstName("zhengshi");
        u.setUsername("dameinv");
        u.setAccountExpired(false);
        u.setEnabled(true);
        u.setCredentialExpired(false);
        u.setAccountLocked(false);
        registrationEmail.confirmEmail(u,root);
        verify(javaMailSender,times(1)).createMimeMessage();
        verify(javaMailSender,times(1)).send(any(MimeMessage.class));
    }
}
