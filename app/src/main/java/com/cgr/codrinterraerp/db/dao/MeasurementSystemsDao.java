package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.MeasurementSystems;

import java.util.List;

@Dao
public interface MeasurementSystemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeasurementSystems(List<MeasurementSystems> measurementSystemsList);

    @Query("SELECT * FROM measurement_systems WHERE productTypeId = :productTypeId ORDER BY id ASC")
    List<MeasurementSystems> getAllMeasurementSystems(int productTypeId);

    @Query("DELETE FROM measurement_systems")
    void clearAll();
}