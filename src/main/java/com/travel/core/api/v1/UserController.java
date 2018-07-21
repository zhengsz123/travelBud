package com.travel.core.api.v1;

import com.travel.core.domain.*;
import com.travel.core.extend.security.JwtTokenUtil;
import com.travel.core.repository.AuthorityRepository;
import com.travel.core.service.AuthorityService;
import com.travel.core.service.GasService;
import com.travel.core.service.StorageService;
import com.travel.core.service.UserService;
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
import org.springframework.web.multipart.MultipartFile;

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
        JwtTokenResponse response = new JwtTokenResponse();
        response.setTokenToJason(token);
        return ResponseEntity.ok(response);


    }

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public User userListPost(@RequestBody  User user ){
        userService.save(user);
        userService.registerUser(user);
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

    @Autowired
    private StorageService storageService;
    @RequestMapping( value = "/image",method = RequestMethod.POST)
    @ResponseBody
    public Media uploadUserImage(@RequestParam ("pic") MultipartFile image){
        logger.debug(image.getName());
        storageService.putObjectApiImplementation(image);
        return null;
    }


    @RequestMapping(value ="/getImageInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Media getImage(@PathVariable Long id){
        Media media = storageService.getImageInfo(id);
        return media ;
    }

    @RequestMapping(value ="/downloadImage/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Media downloadImage(@PathVariable Long id){
        storageService.downloadImage(id);
        return null ;
    }


}
