package com.travel.core.email;



import com.travel.core.domain.User;

import java.io.Serializable;
import java.util.Map;

public class RegistrationEmail extends AbstractFreemarkerEmail {
    @Override
    public String getMailSubject(){
        return "This is a non-reply confirm email";
    }

    @Override
    protected void putValue(User user, Map<String, Serializable> root) {
        //String token = user.getConfirmToken();
        //String url = getMainUrl() + "/users/verification?confirmation_token=" + token;
        //root.put("activation_url", url);
        root.put("url", getMainUrl());
    }


}
