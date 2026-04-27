package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.MeasurementSystemFormulaVariables;

import java.util.List;

@Dao
public interface MeasurementSystemFormulaVariablesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeasurementSystemFormulaVariables(List<MeasurementSystemFormulaVariables> measurementSystemFormulaVariablesList);

    @Query("DELETE FROM measurement_system_formula_variables")
    void clearAll();
}