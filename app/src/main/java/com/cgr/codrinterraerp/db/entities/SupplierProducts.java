package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "supplier_products",
        indices = {
                @Index(name = "idx_supplier_product_id", value = {"supplierProductId"}),
                @Index(name = "idx_supplier_id_products", value = {"supplierId"}),
                @Index(name = "idx_product_id_products", value = {"productId"}),
                @Index(name = "idx_product_name_products", value = {"productName"})
        }
)
public class SupplierProducts implements Serializable {

    @PrimaryKey
    private int supplierProductId;
    private int supplierId;
    private int productId;
    private String productName;

    public int getSupplierProductId() {
        return supplierProductId;
    }

    public void setSupplierProductId(int supplierProductId) {
        this.supplierProductId = supplierProductId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
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
}