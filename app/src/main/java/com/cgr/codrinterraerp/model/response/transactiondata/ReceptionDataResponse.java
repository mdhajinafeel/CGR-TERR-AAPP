package com.cgr.codrinterraerp.model.response.transactiondata;

import java.io.Serializable;

public class ReceptionDataResponse implements Serializable {

    private String tempReceptionDataId, tempReceptionId, containerReceptionMappingId;
    private int receptionDataId, receptionId, pieces;
    private double circumference, length, thickness, width, grossVolume, netVolume, volumePie;
    private long createdAt, updatedAt;

    public String getTempReceptionDataId() {
        return tempReceptionDataId;
    }

    public void setTempReceptionDataId(String tempReceptionDataId) {
        this.tempReceptionDataId = tempReceptionDataId;
    }

    public String getTempReceptionId() {
        return tempReceptionId;
    }

    public void setTempReceptionId(String tempReceptionId) {
        this.tempReceptionId = tempReceptionId;
    }

    public String getContainerReceptionMappingId() {
        return containerReceptionMappingId;
    }

    public void setContainerReceptionMappingId(String containerReceptionMappingId) {
        this.containerReceptionMappingId = containerReceptionMappingId;
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

    public double getCircumference() {
        return circumference;
    }

    public void setCircumference(double circumference) {
        this.circumference = circumference;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
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

    public double getVolumePie() {
        return volumePie;
    }

    public void setVolumePie(double volumePie) {
        this.volumePie = volumePie;
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
}