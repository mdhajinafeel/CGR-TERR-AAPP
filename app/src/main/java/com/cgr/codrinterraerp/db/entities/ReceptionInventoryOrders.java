package com.cgr.codrinterraerp.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "reception_inventory_orders",
        indices = {
                @Index(name = "idx_inventory_order_reception", value = {"inventoryOrder"}),
                @Index(name = "idx_supplier_id_inventory", value = {"supplierId"})
        }
)
public class ReceptionInventoryOrders implements Serializable {

    @PrimaryKey
    @NonNull
    private String inventoryOrder = "";
    private int supplierId;

    @NonNull
    public String getInventoryOrder() {
        return inventoryOrder;
    }

    public void setInventoryOrder(@NonNull String inventoryOrder) {
        this.inventoryOrder = inventoryOrder;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}