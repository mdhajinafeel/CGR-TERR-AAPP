package com.cgr.codrinterraerp.model.response.masterdata;

import java.io.Serializable;
import java.util.List;

public class SuppliersResponse implements Serializable {

    private int supplierId;
    private String supplierName;
    private List<SupplierProductsResponse> supplierProducts;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<SupplierProductsResponse> getSupplierProducts() {
        return supplierProducts;
    }

    public void setSupplierProducts(List<SupplierProductsResponse> supplierProducts) {
        this.supplierProducts = supplierProducts;
    }
}