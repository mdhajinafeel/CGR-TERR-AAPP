package com.cgr.codrinterraerp.db.views;

import androidx.room.DatabaseView;

import java.io.Serializable;

@DatabaseView(
        viewName = "dispatch_view",
        value = "SELECT  d.id, d.tempDispatchId, d.dispatchId, d.containerNumber, d.dispatchDate, s.shippingLine, " +
                "IFNULL(ds.totalPieces,0) as totalPieces, IFNULL(ds.totalGrossVolume,0) as totalGrossVolume, " +
                "IFNULL(ds.totalNetVolume,0) as totalNetVolume, IFNULL(ds.totalVolumePie,0) as totalVolumePie," +
                "IFNULL(ds.avgGirth,0) as avgGirth, IFNULL(ds.cft,0) as cft, d.isClosed, d.productTypeId " +
                "FROM dispatch_details d " +
                "INNER JOIN shipping_lines s ON s.id = d.shippingLineId " +
                "LEFT JOIN dispatch_summary ds ON (ds.dispatchId = d.dispatchId OR ds.tempDispatchId = d.tempDispatchId) " +
                "WHERE isDeleted = 0"
)
public class DispatchView implements Serializable {

    public int id, dispatchId, totalPieces, productTypeId;

    public String tempDispatchId, containerNumber, dispatchDate, shippingLine;

    public double totalGrossVolume, totalNetVolume, totalVolumePie, cft, avgGirth;

    public boolean isClosed;
}
