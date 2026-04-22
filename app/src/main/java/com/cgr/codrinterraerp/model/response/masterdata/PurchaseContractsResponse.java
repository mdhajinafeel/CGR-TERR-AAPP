package com.cgr.codrinterraerp.model.response.masterdata;

import java.io.Serializable;

public class PurchaseContractsResponse implements Serializable {

    private int contractId, supplierId, product, productType, purchaseUnitId;
    private String contractCode, purchaseUnit, currency, description;
    private double purchaseAllowance, purchaseAllowanceLength;

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public int getPurchaseUnitId() {
        return purchaseUnitId;
    }

    public void setPurchaseUnitId(int purchaseUnitId) {
        this.purchaseUnitId = purchaseUnitId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getPurchaseUnit() {
        return purchaseUnit;
    }

    public void setPurchaseUnit(String purchaseUnit) {
        this.purchaseUnit = purchaseUnit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPurchaseAllowance() {
        return purchaseAllowance;
    }

    public void setPurchaseAllowance(double purchaseAllowance) {
        this.purchaseAllowance = purchaseAllowance;
    }

    public double getPurchaseAllowanceLength() {
        return purchaseAllowanceLength;
    }

    public void setPurchaseAllowanceLength(double purchaseAllowanceLength) {
        this.purchaseAllowanceLength = purchaseAllowanceLength;
    }
}