package com.travel.core.api.v1;

import com.travel.core.domain.User;
import com.travel.core.repository.GasRepository;
import com.travel.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping (value = "/api/price", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceController {
    @Autowired
    private CrudRepository userRepository;

    @RequestMapping(value ="method1", method = RequestMethod.GET)
    @ResponseBody
    public List getUserList(){
        return null;
    }

    @RequestMapping(value = "method2",method = RequestMethod.POST)
    @ResponseBody

    public User getUserListPost(@RequestBody  User user){
        userRepository.save(user);
        return user;
    }

}