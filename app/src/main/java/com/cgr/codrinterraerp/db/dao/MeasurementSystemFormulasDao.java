package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.cgr.codrinterraerp.db.entities.MeasurementSystemFormulas;
import com.cgr.codrinterraerp.db.relations.FormulaWithVariables;

import java.util.List;

@Dao
public interface MeasurementSystemFormulasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeasurementSystemFormulas(List<MeasurementSystemFormulas> measurementSystemFormulasList);

    @Transaction
    @Query("SELECT * FROM measurement_system_formulas WHERE measurementSystemId = :msId")
    List<FormulaWithVariables> getFormulasWithVariables(int msId);

    @Query("DELETE FROM measurement_system_formulas")
    void clearAll();
}