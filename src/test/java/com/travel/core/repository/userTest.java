package com.travel.core.repository;

import com.travel.core.config.AppConfig;
import com.travel.core.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static com.sun.deploy.util.SessionState.save;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class userTest {
    @Test
    @Transactional

    public void findByIdTest(){
        User tester = new User();
        tester.setFirstName("Morgan");
        tester.setLastName("Zhang");
//        .save(tester);

    }

}
