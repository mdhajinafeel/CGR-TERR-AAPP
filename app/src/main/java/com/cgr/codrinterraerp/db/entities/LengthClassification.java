package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "length_classification",
        indices = {
                @Index(name = "idx_id_length_classification", value = {"id"}),
                @Index(name = "idx_length_classification", value = {"lengthClassification"})
        }
)
public class LengthClassification implements Serializable {

    @PrimaryKey
    private int id;
    private String lengthClassification;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLengthClassification() {
        return lengthClassification;
    }

    public void setLengthClassification(String lengthClassification) {
        this.lengthClassification = lengthClassification;
    }
}