package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.ReceptionSummary;

@Dao
public interface ReceptionSummaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(ReceptionSummary summary);

    @Query("SELECT * FROM reception_summary WHERE receptionId = :receptionId LIMIT 1")
    ReceptionSummary getByReceptionId(Integer receptionId);

    @Query("SELECT * FROM reception_summary WHERE tempReceptionId = :tempId LIMIT 1")
    ReceptionSummary getByTempId(String tempId);

    // 🔥 IMPORTANT: temp → real mapping
    @Query("UPDATE reception_summary SET receptionId = :receptionId, tempReceptionId = NULL WHERE tempReceptionId = :tempId")
    void mapTempToReal(Integer receptionId, String tempId);
}