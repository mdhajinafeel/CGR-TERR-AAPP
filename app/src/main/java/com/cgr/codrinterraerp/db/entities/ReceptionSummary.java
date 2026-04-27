package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "reception_summary",
        indices = {
                @Index(value = {"receptionId"}, unique = true),
                @Index(value = {"tempReceptionId"}, unique = true)
        }
)
public class ReceptionSummary implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public Integer receptionId;       // server ID (after sync)
    public String tempReceptionId;    // local ID (before sync)

    public int totalPieces;
    public double totalGrossVolume;
    public double totalNetVolume;

    public long updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(Integer receptionId) {
        this.receptionId = receptionId;
    }

    public String getTempReceptionId() {
        return tempReceptionId;
    }

    public void setTempReceptionId(String tempReceptionId) {
        this.tempReceptionId = tempReceptionId;
    }

    public int getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(int totalPieces) {
        this.totalPieces = totalPieces;
    }

    public double getTotalGrossVolume() {
        return totalGrossVolume;
    }

    public void setTotalGrossVolume(double totalGrossVolume) {
        this.totalGrossVolume = totalGrossVolume;
    }

    public double getTotalNetVolume() {
        return totalNetVolume;
    }

    public void setTotalNetVolume(double totalNetVolume) {
        this.totalNetVolume = totalNetVolume;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}