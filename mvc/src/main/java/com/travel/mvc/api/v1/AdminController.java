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

import java.util.List;

@RestController
@RequestMapping(value = {"/api/admin"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authority", method = RequestMethod.GET)
    @ResponseBody
    public List <Authority> getAuthority() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByEmailOrUsername(userDetails.getUsername());
       List <Authority > authority = authorityService.findAuthoritiesByUserId(user);
        return authority;
    }

    @RequestMapping(value = "/authority/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateAuthority(@PathVariable Long id,@RequestParam("authority") String authority) {

    }
}
