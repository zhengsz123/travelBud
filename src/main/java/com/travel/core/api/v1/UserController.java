package com.travel.core.api.v1;

import com.travel.core.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/users/","/api/user"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private CrudRepository userRepository;
    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping( value = "/login/",method = RequestMethod.POST)
    @ResponseBody
    public List printUserLogin( @RequestParam("username") String userName){
        logger.debug(userName);
        return null;
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody

    public User userListPost(@RequestBody  User user){
        userRepository.save(user);
        return user;
    }
}
