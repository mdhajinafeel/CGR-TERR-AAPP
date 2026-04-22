package com.cgr.codrinterraerp.model.response.masterdata;

import java.io.Serializable;

public class LengthClassificationResponse implements Serializable {

    private int id;
    private String lengthClassification;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLengthClassification() {
        return lengthClassification;
    }

    public void setLengthClassification(String lengthClassification) {
        this.lengthClassification = lengthClassification;
    }
}