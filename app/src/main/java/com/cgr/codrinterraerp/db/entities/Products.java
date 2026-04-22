package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "products",
        indices = {
                @Index(name = "idx_product_id_product", value = {"productId"}),
                @Index(name = "idx_product_name_product", value = {"productName"})
        }
)
public class Products implements Serializable {

    @PrimaryKey
    private int productId;
    private String productName;

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