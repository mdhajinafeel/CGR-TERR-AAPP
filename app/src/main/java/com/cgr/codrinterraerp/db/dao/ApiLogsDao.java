package com.cgr.codrinterraerp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.ApiLogs;
import com.cgr.codrinterraerp.model.LogCount;

import java.util.List;

@Dao
public interface ApiLogsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertApiLogs(ApiLogs apiLog);

    @Query("SELECT * FROM api_logs ORDER BY createdAt DESC")
    LiveData<List<ApiLogs>> getAllApiLogs();

    @Query("SELECT * FROM api_logs WHERE type = :type ORDER BY createdAt DESC")
    LiveData<List<ApiLogs>> getApiLogsByType(String type);

    @Query("DELETE FROM api_logs")
    void clearAll();

    @Query("DELETE FROM api_logs WHERE createdAt < :time")
    void deleteOldLogs(long time);

    @Query("DELETE FROM api_logs WHERE id NOT IN (SELECT id FROM api_logs ORDER BY createdAt DESC LIMIT 500)")
    void keepLast500();

    @Query("DELETE FROM api_logs WHERE type = :type")
    void clearLogsByType(String type);

    @Query("DELETE FROM api_logs WHERE id = :id AND createdAt = :createdAt")
    void clearLog(int id, long createdAt);

    @Query("SELECT type, COUNT(*) as count FROM api_logs GROUP BY type")
    LiveData<List<LogCount>> getLogCounts();
}