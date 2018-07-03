package com.travel.core.service;

import com.travel.core.domain.Authority;
import com.travel.core.domain.User;
import com.travel.core.repository.AuthorityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, MessageSourceAware {
    @Autowired
    private UserService userService;
    @Autowired
    protected MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailorUsername) {
        logger.debug(emailorUsername+" is trying to log in from spring security");
        User domainUser = null;
        try {
            domainUser = userService.findByEmailOrUsername(emailorUsername);
        }catch (Exception repositoryProblem) {
            logger.debug("catch AuthenticationServiceException from AuthenticationProvider");
        }
        if (domainUser == null) {
            throw new BadCredentialsException(messageSource.getMessage("AbstractUserDetailsAuthenticationProvider.UsernameNotFound", new Object[] {emailorUsername , "User {0} has no GrantedAuthority"}, Locale.US));
        }
       // domainUser.setAuthorities(Utils.getAuthorities(userService.findAuthorities(domainUser)));
        return  domainUser;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    private AuthorityRepository authorityRepository;
    @Transactional(readOnly = true)
    public List<Authority> findAuthoritiesByUserId(User user){
        List<Authority> authoritiesRoles = authorityRepository.findAuthoritiesByUserId(user.getId());

        return authoritiesRoles;
    }
}