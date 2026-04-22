package com.cgr.codrinterraerp.model.response.masterdata;

import java.io.Serializable;

public class ProductTypesResponse implements Serializable {

    private int typeId;
    private String productTypeName;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
}