package com.travel.core.service;

import com.travel.core.config.AppConfig;
import com.travel.core.config.DatabaseConfig;
import com.travel.core.domain.Authority;
import com.travel.core.domain.User;
import com.travel.core.repository.AuthorityRepository;
import com.travel.core.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class,DatabaseConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class AuthorityServiceTest {
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional

    public void authorityServiceTest() {
        User u = new User();
        Authority a = new Authority();
        a.setAuthorities("manager");
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
        a.setUser(u);
        authorityRepository.save(a);
        userRepository.save(u);
        List<Authority> authoritiesRole = authorityService.findAuthoritiesByUserId(u);
        assertEquals(a.getUser().getId(),authoritiesRole.get(0).getUser().getId());
    }


}
