package com.travel.mvc.api.v1;

import com.travel.core.domain.*;
import com.travel.core.enumdef.WorkerMessageType;
import com.travel.core.service.*;
import com.travel.mvc.extend.security.JwtTokenUtil;
import com.travel.core.service.jms.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/users/","/api/user"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SmsService smsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> printUserLogin(@RequestParam("username") String username, @RequestParam("password") String password, Device device) {
        logger.debug(username);
        logger.debug(password);
        try {
            Authentication notFullyAuthenticated = new UsernamePasswordAuthenticationToken(username, password);

            final Authentication authentication = authenticationManager.authenticate(notFullyAuthenticated);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            logger.debug("", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication not passed");
        }

        User userDetail = userService.findByEmailOrUsername(username);
        String token = jwtTokenUtil.generateToken(userDetail, device);
        JwtTokenResponse response = new JwtTokenResponse();
        response.setTokenToJason(token);
        return ResponseEntity.ok(response);
    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public User userListPost(@RequestBody User user) {
        userService.save(user);
        userService.registerUser(user);
        return user;
    }
/////////////////


/////////////////////
    @Autowired
    private GasService gasService;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Gas gasPriceUpdate(@RequestBody Gas gas) {
        gasService.updateGasType(gas);
        return gas;
    }

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    public Media uploadUserImage(@RequestParam("pic") MultipartFile image) {
        logger.debug(image.getName());
        storageService.putObjectApiImplementation(image);
        return null;
    }

    @RequestMapping(value = "/getImageInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Media getImage(@PathVariable Long id) {
        Media media = storageService.getImageInfo(id);
        return media;
    }

    @RequestMapping(value = "/downloadImage/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Media downloadImage(@PathVariable Long id) {
        storageService.downloadImage(id);
        return null;
    }

    @RequestMapping(value = "/email/{id}", method = RequestMethod.POST)
    @ResponseBody
    public User sendEmail(@PathVariable long id) {
        String idString = String.valueOf(id);
        messageService.sendMessage(WorkerMessageType.UserSignUpMsg,idString,5000);
        return null;
    }

    @RequestMapping(value = "/sms/{id}", method = RequestMethod.POST)
    @ResponseBody
    public User sendSMS(@PathVariable long id){
        String idString = String.valueOf(id);
        messageService.sendMessage(WorkerMessageType.UserSignUpTextMessage,idString,5000);
        //smsService.sendConfirmTextMessage(id);
        return null;
    }
}
