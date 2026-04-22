package com.cgr.codrinterraerp.model.response.masterdata;

import java.io.Serializable;

public class ReceptionInventoryOrdersResponse implements Serializable {

    private String inventoryOrder;
    private int supplierId;

    public String getInventoryOrder() {
        return inventoryOrder;
    }

    public void setInventoryOrder(String inventoryOrder) {
        this.inventoryOrder = inventoryOrder;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}