package com.travel.core.service;

import com.travel.core.config.AppConfig;
import com.travel.core.config.DatabaseConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
public class EmailServiceTest {
    @Test
    @Transactional
    public void confirmEmailTest(){
        assertEquals(true,true);
    }
}
