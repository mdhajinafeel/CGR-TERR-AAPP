package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "girth_classification",
        indices = {
                @Index(name = "idx_id_girth_classification", value = {"id"}),
                @Index(name = "idx_girth_classification", value = {"girthClassification"})
        }
)
public class GirthClassification implements Serializable {

    @PrimaryKey
    private int id;
    private String girthClassification;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGirthClassification() {
        return girthClassification;
    }

    public void setGirthClassification(String girthClassification) {
        this.girthClassification = girthClassification;
    }
}