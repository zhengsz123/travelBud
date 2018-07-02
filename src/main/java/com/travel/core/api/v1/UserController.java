package com.travel.core.api.v1;

import com.travel.core.domain.Gas;
import com.travel.core.domain.User;
import com.travel.core.service.GasService;
import com.travel.core.service.UserService;
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
    public List printUserLogin( @RequestParam("userName") String userName){
        logger.debug(userName);
        return null;
    }

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public User userListPost(@RequestBody  User user){
        userService.save(user);
        return user;
    }

    @Autowired
    private GasService gasService;
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Gas gasPriceUpdate(@RequestBody Gas gas){
        gasService.updateGasPrice(gas);
        return gas;

 }


}
