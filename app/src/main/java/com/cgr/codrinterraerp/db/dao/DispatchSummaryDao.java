package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Upsert;

import com.cgr.codrinterraerp.db.entities.DispatchSummary;

import java.util.List;

@Dao
public interface DispatchSummaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(DispatchSummary summary);

    @Upsert
    void upsert(List<DispatchSummary> summaries);
}