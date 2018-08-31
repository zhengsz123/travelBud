package com.travel.core.service;

import com.travel.core.config.DatabaseConfig;
import com.travel.mvc.config.AppConfig;
import com.travel.core.domain.User;
import com.travel.core.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class,DatabaseConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void userServiceTest(){
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
        userRepository.save(u);
        User testU = userService.findByEmailOrUsername("zhengsz@vt.edu");
        assertNotNull(testU);
        assertEquals(u.getId(),testU.getId());

    }


}
