package com.travel.core.service;

import com.travel.core.domain.User;
import com.travel.core.repository.UserRepository;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SmsService {
    @Value("#{databaseProperties['twilio.ACCOUNT_SID']}")
    private  String ACCOUNT_SID;
    @Value("#{databaseProperties['twilio.AUTH_TOKEN']}")
    private String AUTH_TOKEN;
    @Value("#{databaseProperties['twilio.TWILIO_NUMBER']}")
    private String TWILIO_NUMBER;

    @Autowired
    private UserRepository userRepository;
    public void sendConfirmTextMessage(Long id) {
        Optional<User> optional = userRepository.findById(id);
        User user = optional.get();

       TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        try {
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("Body", "You have successfully signed up for the Travelbud, " +
                    "Welcome!"));
            params.add(new BasicNameValuePair("To", user.getPhoneNumber())); //Add real number here
            params.add(new BasicNameValuePair("From", TWILIO_NUMBER));
            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);

        } catch (TwilioRestException e) {

        }
    }
}
