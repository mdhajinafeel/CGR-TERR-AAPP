package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "warehouses",
        indices = {
                @Index(name = "idx_id_warehouse", value = {"id"}),
                @Index(name = "idx_warehouse_name", value = {"warehouseName"})
        }
)
public class Warehouses implements Serializable {

    @PrimaryKey
    private int id;
    private String warehouseName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}