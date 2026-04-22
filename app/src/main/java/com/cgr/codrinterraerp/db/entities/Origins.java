package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "origins",
        indices = {
            @Index(name = "idx_origin_id", value = {"originId"}),
            @Index(name = "idx_origin_name", value = {"originName"})
        }
)
public class Origins implements Serializable {

    @PrimaryKey
    private int originId;
    private String originName;

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }
}