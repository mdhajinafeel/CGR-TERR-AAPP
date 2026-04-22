package com.cgr.codrinterraerp.model.response;

import java.io.Serializable;
import java.util.List;

public class OriginsResponse implements Serializable {

    private boolean status;
    private String message;
    private List<OriginDataResponse> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OriginDataResponse> getData() {
        return data;
    }

    public void setData(List<OriginDataResponse> data) {
        this.data = data;
    }
}