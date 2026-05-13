package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Upsert;

import com.cgr.codrinterraerp.db.entities.ReceptionSummary;

import java.util.List;

@Dao
public interface ReceptionSummaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(ReceptionSummary summary);

    @Upsert
    void upsert(List<ReceptionSummary> summaries);
}