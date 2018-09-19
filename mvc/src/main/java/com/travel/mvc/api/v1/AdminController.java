package com.travel.mvc.api.v1;

import com.travel.core.domain.Authority;
import com.travel.core.domain.User;
import com.travel.core.service.AuthorityService;
import com.travel.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/api/admin"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authority", method = RequestMethod.PUT)
    @ResponseBody
    public Authority updateAuthority() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user= userService.findByEmailOrUsername(userDetails.getUsername());
        Authority authority = authorityService.findAuthoritiesByUserId(user);
        authority.setAuthorities("TESTING");   
        return authority;
    }
}
