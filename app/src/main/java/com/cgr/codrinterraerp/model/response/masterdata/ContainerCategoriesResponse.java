package com.cgr.codrinterraerp.model.response.masterdata;

import java.io.Serializable;

public class ContainerCategoriesResponse implements Serializable {

    private int id, productTypeId;
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}