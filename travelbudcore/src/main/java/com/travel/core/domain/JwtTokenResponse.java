package com.travel.core.domain;

import java.io.Serializable;

public class JwtTokenResponse implements Serializable {
    private String tokenToJason;

    public String setTokenToJason(String tokenToJason) {
        this.tokenToJason = tokenToJason;
        return tokenToJason;
    }

    public String getTokenToJason() {
        return tokenToJason;
    }
}
