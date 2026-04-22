package com.cgr.codrinterraerp.model.response;

import java.io.Serializable;

public class OriginDataResponse implements Serializable {

    private int originId;
    private String originName;

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }
}