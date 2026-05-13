package com.cgr.codrinterraerp.model.response.transactiondata;

import java.io.Serializable;
import java.util.List;

public class ReceptionDetailsResponse implements Serializable {

    private int receptionId, warehouseId, supplierId, supplierProductId, supplierProductTypeId, measurementSystemId, closedBy, contractId, productId, productTypeId;
    private String receivedDate, ica, truckPlateNumber, tempReceptionId, truckDriverName, containerReceptionMappingId;
    private boolean isClosed, isCreateFarm;
    private long closedDate, capturedTimestamp, updatedAt;
    private double totalGrossVolume, totalNetVolume, totalVolumePie, totalPieces;
    private List<ReceptionDataResponse> receptionData;

    public int getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(int receptionId) {
        this.receptionId = receptionId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getSupplierProductId() {
        return supplierProductId;
    }

    public void setSupplierProductId(int supplierProductId) {
        this.supplierProductId = supplierProductId;
    }

    public int getSupplierProductTypeId() {
        return supplierProductTypeId;
    }

    public void setSupplierProductTypeId(int supplierProductTypeId) {
        this.supplierProductTypeId = supplierProductTypeId;
    }

    public int getMeasurementSystemId() {
        return measurementSystemId;
    }

    public void setMeasurementSystemId(int measurementSystemId) {
        this.measurementSystemId = measurementSystemId;
    }

    public int getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(int closedBy) {
        this.closedBy = closedBy;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getIca() {
        return ica;
    }

    public void setIca(String ica) {
        this.ica = ica;
    }

    public String getTruckPlateNumber() {
        return truckPlateNumber;
    }

    public void setTruckPlateNumber(String truckPlateNumber) {
        this.truckPlateNumber = truckPlateNumber;
    }

    public String getTempReceptionId() {
        return tempReceptionId;
    }

    public void setTempReceptionId(String tempReceptionId) {
        this.tempReceptionId = tempReceptionId;
    }

    public String getTruckDriverName() {
        return truckDriverName;
    }

    public void setTruckDriverName(String truckDriverName) {
        this.truckDriverName = truckDriverName;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public boolean isCreateFarm() {
        return isCreateFarm;
    }

    public void setCreateFarm(boolean createFarm) {
        isCreateFarm = createFarm;
    }

    public long getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(long closedDate) {
        this.closedDate = closedDate;
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

    public double getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(double totalPieces) {
        this.totalPieces = totalPieces;
    }

    public double getTotalVolumePie() {
        return totalVolumePie;
    }

    public void setTotalVolumePie(double totalVolumePie) {
        this.totalVolumePie = totalVolumePie;
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

    public long getCapturedTimestamp() {
        return capturedTimestamp;
    }

    public void setCapturedTimestamp(long capturedTimestamp) {
        this.capturedTimestamp = capturedTimestamp;
    }

    public String getContainerReceptionMappingId() {
        return containerReceptionMappingId;
    }

    public void setContainerReceptionMappingId(String containerReceptionMappingId) {
        this.containerReceptionMappingId = containerReceptionMappingId;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ReceptionDataResponse> getReceptionData() {
        return receptionData;
    }

    public void setReceptionData(List<ReceptionDataResponse> receptionData) {
        this.receptionData = receptionData;
    }
}