package com.cgr.codrinterraerp.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "dispatch_summary",
        indices = {
                @Index(value = {"tempDispatchId"}, unique = true)
        }
)
public class DispatchSummary implements Serializable {

    @PrimaryKey
    @NonNull
    public String tempDispatchId = "";
    public int totalPieces;
    public double totalGrossVolume;
    public double totalNetVolume;
    public double avgGirth;
    public double cft;
    public double totalVolumePie;
    public long updatedAt;

    @NonNull
    public String getTempDispatchId() {
        return tempDispatchId;
    }

    public void setTempDispatchId(@NonNull String tempDispatchId) {
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

    public double getCft() {
        return cft;
    }

    public void setCft(double cft) {
        this.cft = cft;
    }

    public double getTotalVolumePie() {
        return totalVolumePie;
    }

    public void setTotalVolumePie(double totalVolumePie) {
        this.totalVolumePie = totalVolumePie;
    }
}