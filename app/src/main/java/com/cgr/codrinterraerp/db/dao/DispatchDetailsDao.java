package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.cgr.codrinterraerp.db.entities.DispatchDetails;

@Dao
public interface DispatchDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertOrUpdateDispatchDetails(DispatchDetails dispatchDetails);
}