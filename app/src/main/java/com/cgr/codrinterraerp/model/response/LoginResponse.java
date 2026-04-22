package com.cgr.codrinterraerp.model.response;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private boolean status;
    private LoginDataResponse data;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LoginDataResponse getData() {
        return data;
    }

    public void setData(LoginDataResponse data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}