package com.travel.core.service;


import com.travel.core.config.DatabaseConfig;
import com.travel.mvc.config.AppConfig;
import com.travel.core.domain.User;
import com.travel.mvc.extend.security.JwtTokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.LiteDevice;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;


@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class,DatabaseConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class JwtTokenUtilTest {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    @Test
    @Transactional
    public void ifJWTWorks() {
        User u = new User();
        u.setEmail("zhengsz@vt.edu");
        u.setPassword("19941227");
        u.setLastName("zhang");
        u.setFirstName("zhengshi");
        u.setUsername("dameinv");
        u.setAccountExpired(false);
        u.setEnabled(true);
        u.setCredentialExpired(false);
        u.setAccountLocked(false);
        u.setAccountLocked(true);
        u.setCredentialExpired(false);
        u.setEnabled(true);
        u.setAccountExpired(false);
        userService.save(u);
        String token = jwtTokenUtil.generateToken(u,new LiteDevice());
        String testUsername = jwtTokenUtil.getUsernameFromToken(token);
        assertEquals(u.getUsername(),testUsername);
    }
    }
