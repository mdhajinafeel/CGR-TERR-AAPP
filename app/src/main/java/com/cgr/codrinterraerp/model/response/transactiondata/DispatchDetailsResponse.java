package com.cgr.codrinterraerp.model.response.transactiondata;

import java.io.Serializable;
import java.util.List;

public class DispatchDetailsResponse implements Serializable {

    private int dispatchId, warehouseId, shippingLineId, productId, productTypeId, closedBy, totalPieces, categoryId;
    private String containerNumber, dispatchDate, closedDate, tempDispatchId;
    private boolean isClosed;
    private double totalGrossVolume, totalNetVolume;
    private long createdAt, updatedAt;
    private List<ContainerDataResponse> containerData;

    public int getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(int dispatchId) {
        this.dispatchId = dispatchId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getShippingLineId() {
        return shippingLineId;
    }

    public void setShippingLineId(int shippingLineId) {
        this.shippingLineId = shippingLineId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public int getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(int closedBy) {
        this.closedBy = closedBy;
    }

    public int getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(int totalPieces) {
        this.totalPieces = totalPieces;
    }

    public String getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(String containerNumber) {
        this.containerNumber = containerNumber;
    }

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getTempDispatchId() {
        return tempDispatchId;
    }

    public void setTempDispatchId(String tempDispatchId) {
        this.tempDispatchId = tempDispatchId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
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

    public List<ContainerDataResponse> getContainerData() {
        return containerData;
    }

    public void setContainerData(List<ContainerDataResponse> containerData) {
        this.containerData = containerData;
    }
}