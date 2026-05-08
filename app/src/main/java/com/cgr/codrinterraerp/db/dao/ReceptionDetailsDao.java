package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.ReceptionDetails;

@Dao
public interface ReceptionDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertOrUpdateReceptionDetails(ReceptionDetails receptionDetails);

    @Query("SELECT * FROM reception_details WHERE tempReceptionId = :tempReceptionId AND isDeleted = 0")
    ReceptionDetails fetchReceptionDetailById(String tempReceptionId);

    @Query("SELECT COUNT(*) FROM reception_details " +
            "WHERE ica = :inventoryOrder " +
            "AND supplierId = :supplierId " +
            "AND tempReceptionId != :tempReceptionId " +
            "AND isDeleted = 0")
    int getReceptionInventoryOrdersCountForEdit(String inventoryOrder, int supplierId, String tempReceptionId);

    @Query("UPDATE reception_details SET isEdited = 1, isSynced = 0, updatedAt = :updatedAt, isDeleted = 1 WHERE tempReceptionId = :tempReceptionId")
    int deleteReceptionDetails(String tempReceptionId, long updatedAt);

    @Query("UPDATE reception_details SET isClosed = :isClose, closedBy = :closedBy, closedDate = :closedDate WHERE tempReceptionId = :tempReceptionId")
    int closeReceptionDetails(String tempReceptionId, String closedDate, int closedBy, boolean isClose);
}