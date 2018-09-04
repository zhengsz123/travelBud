package com.travelbud.apicoreboot.Jms;


import com.travel.core.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@EnableAutoConfiguration
public class MyListener {
   @Autowired
   EmailService emailService;

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @JmsListener(destination = "${jms.queue.name}")
    public void processMessage(Map<String,Object> msg){
        String msgType = (String)msg.get("msgType");
        String msgText = (String)msg.get("msgText");
        Long userID = Long.valueOf(msgText);
        Long id = Long.parseLong(msgText);
        emailService.sendConfirmEmail(id);
        logger.info("receive msgType: "+msgType);
        logger.info("receive msgText: "+msgText);
    }
}
