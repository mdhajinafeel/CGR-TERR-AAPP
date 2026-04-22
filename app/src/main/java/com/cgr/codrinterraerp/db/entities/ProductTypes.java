package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "product_types",
        indices = {
                @Index(name = "idx_type_id_product_type", value = {"typeId"}),
                @Index(name = "idx_product_type_name_product_type", value = {"productTypeName"})
        }
)
public class ProductTypes implements Serializable {

    @PrimaryKey
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