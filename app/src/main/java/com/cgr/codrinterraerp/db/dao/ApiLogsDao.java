package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.ApiLogs;

import java.util.List;

@Dao
public interface ApiLogsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertApiLogs(ApiLogs apiLog);

    @Query("SELECT * FROM api_logs ORDER BY createdAt DESC")
    List<ApiLogs> getAllApiLogs();

    @Query("DELETE FROM api_logs")
    void clearAll();

    @Query("DELETE FROM api_logs WHERE createdAt < :time")
    void deleteOldLogs(long time);

    @Query("DELETE FROM api_logs WHERE id NOT IN (SELECT id FROM api_logs ORDER BY createdAt DESC LIMIT 500)")
    void keepLast500();
}