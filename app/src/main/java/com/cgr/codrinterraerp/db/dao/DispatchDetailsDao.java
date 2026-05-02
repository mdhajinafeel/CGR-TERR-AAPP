package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.DispatchDetails;

@Dao
public interface DispatchDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertOrUpdateDispatchDetails(DispatchDetails dispatchDetails);

    @Query("SELECT * FROM dispatch_details WHERE tempDispatchId = :tempDispatchId AND isDeleted = 0")
    DispatchDetails fetchDispatchDetailById(String tempDispatchId);

    @Query("SELECT COUNT(*) FROM dispatch_details " +
            "WHERE containerNumber = :containerNumber " +
            "AND shippingLineId = :shippingLineId " +
            "AND tempDispatchId != :tempDispatchId " +
            "AND isDeleted = 0")
    int getDispatchContainersCountForEdit(String containerNumber, int shippingLineId, String tempDispatchId);

    @Query("UPDATE dispatch_details SET isDeleted = 1, updatedAt = :updatedAt WHERE tempDispatchId = :tempDispatchId")
    int deleteDispatch(String tempDispatchId, long updatedAt);
}