package com.cgr.codrinterraerp.model.response.masterdata;

import java.io.Serializable;

public class WarehousesResponse implements Serializable {

    private int id;
    private String warehouseName;

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}