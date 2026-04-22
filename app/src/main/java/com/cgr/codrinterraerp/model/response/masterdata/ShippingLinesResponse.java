package com.cgr.codrinterraerp.model.response.masterdata;

import java.io.Serializable;

public class ShippingLinesResponse implements Serializable {

    private int id;
    private String shippingLine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShippingLine() {
        return shippingLine;
    }

    public void setShippingLine(String shippingLine) {
        this.shippingLine = shippingLine;
    }
}