package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.ContainerData;
import com.cgr.codrinterraerp.db.entities.ContainerImages;
import com.cgr.codrinterraerp.db.entities.DispatchDetails;
import com.cgr.codrinterraerp.db.entities.ReceptionData;
import com.cgr.codrinterraerp.db.entities.ReceptionDetails;

import java.util.List;

@Dao
public interface SyncDao {

    // =====================
    // CONTAINER PHOTOS
    // =====================
    @Query("SELECT * FROM container_images WHERE isDeleted = 0 AND isSynced = 0 ORDER BY createdAt ASC")
    List<ContainerImages> getUnsyncedImages();

    @Query("UPDATE container_images SET serverImageUrl = :url, isSynced=1 WHERE tempContainerImageId=:tempId")
    void updateImageSync(String tempId, String url);

    @Query("UPDATE container_images SET isSynced=0 WHERE tempContainerImageId = :tempId")
    void markFailed(String tempId);

    // =====================
    // RECEPTION
    // =====================
    @Query("SELECT * FROM reception_details WHERE isDeleted = 0 AND isSynced = 0")
    List<ReceptionDetails> getUnsyncedReceptionDetails();

    @Query("UPDATE reception_details SET receptionId=:serverId, isSynced=1, isEdited=0 WHERE tempReceptionId=:tempId")
    void updateReceptionMapping(String tempId,int serverId);

    // =====================
    // RECEPTION DATA
    // =====================
    @Query("SELECT * FROM reception_data WHERE isDeleted = 0 AND isSynced = 0")
    List<ReceptionData> getUnsyncedReceptionData();

    @Query("UPDATE reception_data SET receptionDataId=:serverId, isSynced=1, isEdited=0 WHERE tempReceptionDataId=:tempId")
    void updateReceptionDataMapping(String tempId, int serverId);

    // =====================
    // DISPATCH
    // =====================

    @Query("SELECT rd.*, COALESCE(SUM(rdata.grossVolume), 0) AS totalGrossVolume, COALESCE(SUM(rdata.netVolume), 0) AS totalNetVolume, " +
            "COALESCE(SUM(rdata.volumePie), 0) AS totalVolumePie, COALESCE(SUM(rdata.pieces), 0) AS totalPieces " +
            "FROM reception_details rd LEFT JOIN reception_data rdata ON (rdata.receptionId = rd.receptionId OR rdata.tempReceptionId = rd.tempReceptionId) " +
            "AND rdata.isDeleted = 0 WHERE rd.isSynced = 0 GROUP BY rd.id")
    List<DispatchDetails> getUnsyncedDispatch();

    @Query("UPDATE dispatch_details SET dispatchId=:serverId, isSynced=1, isEdited=0 WHERE tempDispatchId=:tempId")
    void updateDispatchMapping(String tempId, int serverId);

    // =====================
    // CONTAINER DATA
    // =====================

    @Query("SELECT * FROM container_data WHERE isDeleted = 0 AND isSynced = 0")
    List<ContainerData> getUnsyncedContainerData();

    @Query("UPDATE container_data SET isSynced=1, isEdited=0")
    void markContainerDataSynced();
}