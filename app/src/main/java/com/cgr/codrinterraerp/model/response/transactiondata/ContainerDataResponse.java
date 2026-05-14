package com.cgr.codrinterraerp.model.response.transactiondata;

import java.io.Serializable;

public class ContainerDataResponse implements Serializable {

    private int dispatchDataId, dispatchId, receptionDataId, receptionId, pieces;
    private String tempDispatchDataId, tempDispatchId, containerReceptionMappingId, tempReceptionId, tempReceptionDataId;
    private double grossVolume, netVolume, volumePie;
    private long createdAt, updatedAt;

    public int getDispatchDataId() {
        return dispatchDataId;
    }

    public void setDispatchDataId(int dispatchDataId) {
        this.dispatchDataId = dispatchDataId;
    }

    public int getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(int dispatchId) {
        this.dispatchId = dispatchId;
    }

    public int getReceptionDataId() {
        return receptionDataId;
    }

    public void setReceptionDataId(int receptionDataId) {
        this.receptionDataId = receptionDataId;
    }

    public int getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(int receptionId) {
        this.receptionId = receptionId;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public String getTempDispatchId() {
        return tempDispatchId;
    }

    public void setTempDispatchId(String tempDispatchId) {
        this.tempDispatchId = tempDispatchId;
    }

    public String getContainerReceptionMappingId() {
        return containerReceptionMappingId;
    }

    public void setContainerReceptionMappingId(String containerReceptionMappingId) {
        this.containerReceptionMappingId = containerReceptionMappingId;
    }

    public String getTempReceptionId() {
        return tempReceptionId;
    }

    public void setTempReceptionId(String tempReceptionId) {
        this.tempReceptionId = tempReceptionId;
    }

    public String getTempReceptionDataId() {
        return tempReceptionDataId;
    }

    public void setTempReceptionDataId(String tempReceptionDataId) {
        this.tempReceptionDataId = tempReceptionDataId;
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

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTempDispatchDataId() {
        return tempDispatchDataId;
    }

    public void setTempDispatchDataId(String tempDispatchDataId) {
        this.tempDispatchDataId = tempDispatchDataId;
    }

    public double getVolumePie() {
        return volumePie;
    }

    public void setVolumePie(double volumePie) {
        this.volumePie = volumePie;
    }
}