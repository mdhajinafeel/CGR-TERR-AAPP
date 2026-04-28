package com.cgr.codrinterraerp.db.views;

import androidx.room.DatabaseView;

import java.io.Serializable;

@DatabaseView(
        viewName = "reception_view",
        value = "SELECT r.id, r.tempReceptionId, r.receptionId, r.ica, " +
                "s.supplierName, m.measurementName, r.receptionDate, " +
                "IFNULL(ds.totalPieces,0) as totalPieces, IFNULL(ds.totalGrossVolume,0) as totalGrossVolume, " +
                "IFNULL(ds.totalNetVolume,0) as totalNetVolume, r.measurementSystem, r.productTypeId " +
                "FROM reception_details r " +
                "INNER JOIN suppliers s ON s.supplierId = r.supplierId " +
                "INNER JOIN measurement_systems m ON m.id = r.measurementSystem " +
                "LEFT JOIN reception_summary ds ON (ds.receptionId = r.receptionId OR ds.tempReceptionId = r.tempReceptionId) " +
                "WHERE r.isDeleted = 0"
)
public class ReceptionView implements Serializable {

    public int id;
    public String tempReceptionId;
    public int receptionId;
    public String ica;
    public String supplierName;
    public String measurementName;
    public String receptionDate;
    public int totalPieces;
    public double totalGrossVolume;
    public double totalNetVolume;
    public int measurementSystem;
    public int productTypeId;
}
