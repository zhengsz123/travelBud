package com.travel.core.repository;

import com.travel.core.config.AppConfig;
import com.travel.core.config.DatabaseConfig;
import com.travel.core.domain.Authority;
import com.travel.core.extend.security.Utils;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class,DatabaseConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class UtilsTest extends Utils{
    @Autowired
    private AuthorityRepository authorityRepository;
    @Test
    @Transactional
    public void ifUtilsWorks(){
        Authority authority = new Authority();
        authority.setAuthorityRole("REGISTERED_USER");
        authorityRepository.save(authority);
        Optional<Authority> authoritiesTest = Utils.getAuthorities();


    }
}
