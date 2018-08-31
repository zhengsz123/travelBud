package com.travel.core.service;

import com.travel.core.Utils;
import com.travel.core.config.AppConfig;
import com.travel.core.config.DatabaseConfig;
import com.travel.core.domain.Authority;
import com.travel.core.repository.AuthorityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class,DatabaseConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class UtilsTest extends Utils {
    @Autowired
    private AuthorityRepository authorityRepository;
    @Test
    @Transactional
    public void ifUtilsWorks(){
        Authority authority = new Authority();
        List <Authority> array = new ArrayList();
        Authority auth1 = new Authority();
        Authority auth2 = new Authority();
        Authority auth3 = new Authority();
        auth1.setAuthorities("registered_user1");
        auth2.setAuthorities("registered_user2");
        auth3.setAuthorities("registered_user3");
        array.add(auth1);
        array.add(auth2);
        array.add(auth3);
        authority.setAuthorities("REGISTERED_USER1");
        authorityRepository.save(authority);
        Collection <GrantedAuthority> authoritiesTest = Utils.getAuthorities(array);
        List<GrantedAuthority> result = new ArrayList<>();
        for(GrantedAuthority ga: authoritiesTest){
            result.add(ga);
        }
        assertEquals(authority.getAuthorities(),result.get(0).getAuthority());
        assertEquals(array.size(),result.size());

    }
}
