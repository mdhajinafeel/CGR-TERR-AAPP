package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.DispatchContainers;

import java.util.List;

@Dao
public interface DispatchContainersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDispatchContainers(List<DispatchContainers> dispatchContainersList);

    @Query("SELECT COUNT(containerNumber) FROM dispatch_containers WHERE containerNumber = :containerNumber AND shippingLineId = :shippingLineId")
    int getDispatchContainersCount(String containerNumber, int shippingLineId);

    @Query("DELETE FROM dispatch_containers")
    void clearAll();
}