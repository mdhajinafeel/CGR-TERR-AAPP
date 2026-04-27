package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "reception_data",
        indices = {
                @Index(name = "idx_temp_id_reception_data", value = {"tempReceptionId"}),
                @Index(name = "idx_reception_id_data", value = {"receptionId"}),
                @Index(name = "idx_deleted_reception_data", value = {"isDeleted"})
        })
public class ReceptionData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String tempReceptionId;
    private Integer receptionId;
    private int circumference;
    private int length;
    private int pieces;
    private double grossVolume;
    private double netVolume;
    private boolean isSynced = false;
    private boolean isDeleted = false;
    private boolean isEdited = false;
    private long updatedAt = System.currentTimeMillis();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTempReceptionId() {
        return tempReceptionId;
    }

    public void setTempReceptionId(String tempReceptionId) {
        this.tempReceptionId = tempReceptionId;
    }

    public Integer getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(Integer receptionId) {
        this.receptionId = receptionId;
    }

    public int getCircumference() {
        return circumference;
    }

    public void setCircumference(int circumference) {
        this.circumference = circumference;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void setEdited(boolean edited) {
        isEdited = edited;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getGrossVolume() {
        return grossVolume;
    }

    public void setGrossVolume(double grossVolume) {
        this.grossVolume = grossVolume;
    }

    public double getNetVolume() {
        return netVolume;
    }

    public void setNetVolume(double netVolume) {
        this.netVolume = netVolume;
    }
}