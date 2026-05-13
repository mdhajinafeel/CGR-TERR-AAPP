package com.cgr.codrinterraerp.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import javax.annotation.Nonnull;

@Entity(
        tableName = "reception_summary",
        indices = {
                @Index(value = {"tempReceptionId"}, unique = true)
        }
)
public class ReceptionSummary implements Serializable {

    @PrimaryKey
    @NonNull
    public String tempReceptionId = "";
    public int totalPieces;
    public double totalGrossVolume;
    public double totalNetVolume;
    public double totalVolumePie;
    public long updatedAt;

    @NonNull
    public String getTempReceptionId() {
        return tempReceptionId;
    }

    public void setTempReceptionId(@NonNull String tempReceptionId) {
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

    public double getTotalVolumePie() {
        return totalVolumePie;
    }

    public void setTotalVolumePie(double totalVolumePie) {
        this.totalVolumePie = totalVolumePie;
    }
}