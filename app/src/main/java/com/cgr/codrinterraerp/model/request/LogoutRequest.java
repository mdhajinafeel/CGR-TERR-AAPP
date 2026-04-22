package com.cgr.codrinterraerp.model.request;

import java.io.Serializable;

public class LogoutRequest implements Serializable {

    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}