package com.cgr.codrinterraerp.model.response.masterdata;

import java.io.Serializable;

public class GirthClassificationResponse implements Serializable {

    private int id;
    private String girthClassification;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGirthClassification() {
        return girthClassification;
    }

    public void setGirthClassification(String girthClassification) {
        this.girthClassification = girthClassification;
    }
}