package com.travel.core.service;


import com.travel.core.domain.Authority;
import com.travel.core.domain.User;
import com.travel.core.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;
    @Transactional(readOnly = true)
    public Authority findAuthoritiesByUserId(User user){
        Authority authoritiesRoles = authorityRepository.findAuthoritiesByUserId(user.getId());
        return authoritiesRoles;
    }
}
