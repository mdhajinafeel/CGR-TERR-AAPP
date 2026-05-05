package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "container_categories",
        indices = {
                @Index(name = "idx_cc_id", value = {"id"}),
                @Index(name = "idx_cc_category", value = {"category"}),
                @Index(name = "idx_cc_product_type_id", value = {"productTypeId"})
        })
public class ContainerCategories implements Serializable {

    @PrimaryKey
    private int id;
    private String category;
    private int productTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }
}