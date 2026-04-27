package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.DispatchSummary;

@Dao
public interface DispatchSummaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(DispatchSummary summary);

    @Query("SELECT * FROM dispatch_summary WHERE dispatchId = :dispatchId LIMIT 1")
    DispatchSummary getByDispatchId(Integer dispatchId);

    @Query("SELECT * FROM dispatch_summary WHERE tempDispatchId = :tempId LIMIT 1")
    DispatchSummary getByTempId(String tempId);

    // 🔥 IMPORTANT: temp → real mapping
    @Query("UPDATE dispatch_summary SET dispatchId = :dispatchId, tempDispatchId = NULL WHERE tempDispatchId = :tempId")
    void mapTempToReal(Integer dispatchId, String tempId);
}