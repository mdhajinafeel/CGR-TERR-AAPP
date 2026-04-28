package com.cgr.codrinterraerp.repository;

import com.cgr.codrinterraerp.db.dao.MeasurementSystemFormulasDao;
import com.cgr.codrinterraerp.db.entities.MeasurementSystemFormulas;
import com.cgr.codrinterraerp.db.relations.FormulaWithVariables;

import java.util.List;

public class ReceptionDataRepository {

    private final MeasurementSystemFormulasDao measurementSystemFormulasDao;

    public ReceptionDataRepository(MeasurementSystemFormulasDao measurementSystemFormulasDao) {
        this.measurementSystemFormulasDao = measurementSystemFormulasDao;
    }

    // FETCH FORMULA
    public FormulaWithVariables getFormulasWithVariables(int measurementSystemId) {
        return measurementSystemFormulasDao.getFormulasWithVariables(measurementSystemId);
    }
}