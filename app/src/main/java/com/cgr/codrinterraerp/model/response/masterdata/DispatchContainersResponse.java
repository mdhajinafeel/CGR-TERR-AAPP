package com.cgr.codrinterraerp.model.response.masterdata;

import java.io.Serializable;

public class DispatchContainersResponse implements Serializable {

    private String containerNumber;
    private int shippingLineId;

    public String getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(String containerNumber) {
        this.containerNumber = containerNumber;
    }

    public int getShippingLineId() {
        return shippingLineId;
    }

    public void setShippingLineId(int shippingLineId) {
        this.shippingLineId = shippingLineId;
    }
}