package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "supplier_product_types",
        indices = {
                @Index(name = "idx_type_id", value = {"typeId"}),
                @Index(name = "idx_supplier_product_id_types", value = {"supplierProductId"}),
                @Index(name = "idx_supplier_id_types", value = {"supplierId"}),
                @Index(name = "idx_product_id_types", value = {"productId"}),
                @Index(name = "idx_product_type_name_types", value = {"productTypeName"}),
                @Index(name = "idx_product_type_id_types", value = {"productTypeId"})
        }
)
public class SupplierProductTypes implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int typeId;
    private int supplierProductId;
    private int supplierId;
    private int productId;
    private String productTypeName;
    private int productTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

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

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }
}