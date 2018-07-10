package com.travel.core.api.v1;

import com.travel.core.domain.Gas;
import com.travel.core.domain.User;
import com.travel.core.extend.security.JwtTokenUtil;
import com.travel.core.service.GasService;
import com.travel.core.service.UserService;
import javassist.NotFoundException;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/users/","/api/user"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private CrudRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @RequestMapping( value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> printUserLogin(@RequestParam("username") String username, @RequestParam("password") String password, Device device){
        logger.debug(username);
        logger.debug(password);
        try {
            Authentication notFullyAuthenticated = new UsernamePasswordAuthenticationToken(username, password);

            final Authentication authentication = authenticationManager.authenticate(notFullyAuthenticated);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch(AuthenticationException e){
            logger.debug("",e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication not passed");
        }

        User userDetail = userService.findByEmailOrUsername(username);
        String token = jwtTokenUtil.generateToken(userDetail,device);
        return ResponseEntity.status((HttpStatus.ACCEPTED)).body(token);


    }

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/singup", method = RequestMethod.POST)
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
