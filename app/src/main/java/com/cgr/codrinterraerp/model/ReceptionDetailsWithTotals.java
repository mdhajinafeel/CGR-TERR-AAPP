package com.cgr.codrinterraerp.model;

import java.io.Serializable;

public class ReceptionDetailsWithTotals implements Serializable {

    // =========================
    // RECEPTION DETAILS
    // =========================

    public long id;
    public String tempReceptionId;
    public Integer receptionId;
    public String ica;

    public int supplierId;
    public int supplierProductId;
    public int productId;

    public int supplierProductTypeId;
    public int productTypeId;

    public int measurementSystem;
    public int warehouse;

    public String receptionDate;

    public boolean isFarmEnabled;

    public int purchaseContract;

    public String truckNumber;
    public String truckDriverName;

    public boolean isSynced;
    public boolean isDeleted;
    public boolean isEdited;

    public long updatedAt;

    public String containerReceptionMappingId;

    public boolean isClosed;
    public int closedBy;
    public String closedDate;

    public long createdAt;

    // =========================
    // SUMMARY TABLE
    // =========================

    public int totalPieces;
    public double totalGrossVolume;
    public double totalNetVolume;
    public double totalVolumePie;
}