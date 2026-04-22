package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "suppliers",
        indices = {
                @Index(name = "idx_supplier_id_suppliers", value = {"supplierId"}),
                @Index(name = "idx_supplier_name", value = {"supplierName"})
        }
)
public class Suppliers implements Serializable {

    @PrimaryKey
    private int supplierId;
    private String supplierName;

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
}