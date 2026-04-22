package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "purchase_contracts",
        indices = {
                @Index(name = "idx_contract_id", value = {"contractId"}),
                @Index(name = "idx_supplier_id", value = {"supplierId"}),
                @Index(name = "idx_contract_code", value = {"contractCode"}),
                @Index(name = "idx_product", value = {"product"}),
                @Index(name = "idx_product_type", value = {"productType"}),
                @Index(name = "idx_purchase_unit", value = {"purchaseUnit"}),
                @Index(name = "idx_purchase_unit_id", value = {"purchaseUnitId"}),
                @Index(name = "idx_currency", value = {"currency"}),
                @Index(name = "idx_purchase_allowance", value = {"purchaseAllowance"}),
                @Index(name = "idx_purchase_allowance_length", value = {"purchaseAllowanceLength"}),
                @Index(name = "idx_description", value = {"description"}),
        }
)
public class PurchaseContracts implements Serializable {

    @PrimaryKey
    private int contractId;
    private int supplierId;
    private String contractCode;
    private int product;
    private int productType;
    private String purchaseUnit;
    private int purchaseUnitId;
    private String currency;
    private double purchaseAllowance;
    private double purchaseAllowanceLength;
    private String description;

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

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
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

    public String getPurchaseUnit() {
        return purchaseUnit;
    }

    public void setPurchaseUnit(String purchaseUnit) {
        this.purchaseUnit = purchaseUnit;
    }

    public int getPurchaseUnitId() {
        return purchaseUnitId;
    }

    public void setPurchaseUnitId(int purchaseUnitId) {
        this.purchaseUnitId = purchaseUnitId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}