package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "shipping_lines",
        indices = {
                @Index(name = "idx_id", value = {"id"}),
                @Index(name = "idx_shipping_line", value = {"shippingLine"})
        }
)
public class ShippingLines implements Serializable {

    @PrimaryKey
    private int id;
    private String shippingLine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShippingLine() {
        return shippingLine;
    }

    public void setShippingLine(String shippingLine) {
        this.shippingLine = shippingLine;
    }
}