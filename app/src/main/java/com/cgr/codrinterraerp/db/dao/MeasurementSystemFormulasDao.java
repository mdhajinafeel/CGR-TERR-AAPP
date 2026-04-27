package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.MeasurementSystemFormulas;

import java.util.List;

@Dao
public interface MeasurementSystemFormulasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeasurementSystemFormulas(List<MeasurementSystemFormulas> measurementSystemFormulasList);

    @Query("DELETE FROM measurement_system_formulas")
    void clearAll();
}