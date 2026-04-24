package com.cgr.codrinterraerp.db.views;

import androidx.room.DatabaseView;

import java.io.Serializable;

@DatabaseView(
        viewName = "reception_view",
        value = "SELECT r.id, r.tempReceptionId, r.receptionId, r.ica, " +
                "s.supplierName, m.measurementName, r.receptionDate, " +
                "0 AS totalPieces, " +
                "0 AS totalGrossVolume, " +
                "0 AS totalNetVolume " +
                "FROM reception_details r " +
                "INNER JOIN suppliers s ON s.supplierId = r.supplierId " +
                "INNER JOIN measurement_systems m ON m.id = r.measurementSystem " +
                "WHERE r.isDeleted = 0"
)
public class ReceptionView implements Serializable {

    public int id;
    public String tempReceptionId;
    public String receptionId;
    public String ica;
    public String supplierName;
    public String measurementName;
    public String receptionDate;
    public int totalPieces;
    public double totalGrossVolume;
    public double totalNetVolume;
}
