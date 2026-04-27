package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "dispatch_summary",
        indices = {
                @Index(value = {"dispatchId"}, unique = true),
                @Index(value = {"tempDispatchId"}, unique = true)
        }
)
public class DispatchSummary implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public Integer dispatchId;       // server ID (after sync)
    public String tempDispatchId;    // local ID (before sync)

    public int totalPieces;
    public double totalGrossVolume;
    public double totalNetVolume;
    public double avgGirth;

    public long updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getTempDispatchId() {
        return tempDispatchId;
    }

    public void setTempDispatchId(String tempDispatchId) {
        this.tempDispatchId = tempDispatchId;
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

    public double getAvgGirth() {
        return avgGirth;
    }

    public void setAvgGirth(double avgGirth) {
        this.avgGirth = avgGirth;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}