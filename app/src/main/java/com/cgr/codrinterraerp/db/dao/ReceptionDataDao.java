package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.cgr.codrinterraerp.db.entities.ReceptionData;

import java.util.List;

@Dao
public interface ReceptionDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReceptionData(List<ReceptionData> receptionDataList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertReceptionData(ReceptionData receptionData);
}