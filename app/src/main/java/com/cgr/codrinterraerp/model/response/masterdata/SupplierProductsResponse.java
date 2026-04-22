package com.cgr.codrinterraerp.model.response.masterdata;

import java.io.Serializable;
import java.util.List;

public class SupplierProductsResponse implements Serializable {

    private int supplierProductId, productId;
    private String productName;
    private List<SupplierProductTypesResponse> supplierProductTypes;

    public int getSupplierProductId() {
        return supplierProductId;
    }

    public void setSupplierProductId(int supplierProductId) {
        this.supplierProductId = supplierProductId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<SupplierProductTypesResponse> getSupplierProductTypes() {
        return supplierProductTypes;
    }

    public void setSupplierProductTypes(List<SupplierProductTypesResponse> supplierProductTypes) {
        this.supplierProductTypes = supplierProductTypes;
    }
}