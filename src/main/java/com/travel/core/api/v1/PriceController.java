package com.travel.core.api.v1;

import com.travel.core.domain.Station;
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
@RequestMapping (value = "/api/price", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceController {
    @Autowired
    private CrudRepository userRepository;
    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List getPriceList(@PathVariable String id){
        logger.debug("get price list api");
        return null;
    }



}