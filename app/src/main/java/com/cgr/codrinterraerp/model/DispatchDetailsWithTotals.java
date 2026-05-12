package com.cgr.codrinterraerp.model;

import java.io.Serializable;

public class DispatchDetailsWithTotals implements Serializable {

    // =========================
    // DISPATCH DETAILS
    // =========================

    public long id;

    public String tempDispatchId;
    public Integer dispatchId;

    public String containerNumber;

    public int productId;
    public int productTypeId;

    public int warehouseId;
    public int shippingLineId;

    public String dispatchDate;

    public int categoryId;

    public boolean isClosed;
    public boolean isSynced;
    public boolean isDeleted;
    public boolean isEdited;

    public long updatedAt;

    public int closedBy;

    public long createdAt;

    public String closedDate;

    // =========================
    // SUMMARY TABLE
    // =========================

    public int totalPieces;
    public double totalGrossVolume;
    public double totalNetVolume;
    public double avgGirth;
    public double cft;
    public double totalVolumePie;
}