package com.travelbud.apicoreboot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class MyListener implements MessageListener {

    @Override
    @JmsListener(destination = "${jms.queue.name}")
    public void onMessage(Message message) {
        try{
            System.out.println("Received: "+((TextMessage)message).getText());
        }
        catch (JMSException e){
            e.printStackTrace();
        }
    }
}
