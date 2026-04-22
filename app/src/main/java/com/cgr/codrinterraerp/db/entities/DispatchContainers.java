package com.cgr.codrinterraerp.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "dispatch_containers",
        indices = {
                @Index(name = "idx_container_number", value = {"containerNumber"}),
                @Index(name = "idx_shipping_line_id", value = {"shippingLineId"})
        }
)
public class DispatchContainers implements Serializable {

    @PrimaryKey
    @NonNull
    private String containerNumber = "";
    private int shippingLineId;

    @NonNull
    public String getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(@NonNull String containerNumber) {
        this.containerNumber = containerNumber;
    }

    public int getShippingLineId() {
        return shippingLineId;
    }

    public void setShippingLineId(int shippingLineId) {
        this.shippingLineId = shippingLineId;
    }
}