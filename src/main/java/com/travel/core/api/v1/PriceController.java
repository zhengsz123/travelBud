package com.travel.core.api.v1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping (value = "/api/price", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceController {
    @RequestMapping(value ="", method = RequestMethod.GET)
    public List getUserList(){
        return null;
    }


}