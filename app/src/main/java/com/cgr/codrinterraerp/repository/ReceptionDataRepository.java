package com.cgr.codrinterraerp.repository;

import com.cgr.codrinterraerp.db.dao.MeasurementSystemFormulasDao;
import com.cgr.codrinterraerp.db.dao.ReceptionTransactionDao;
import com.cgr.codrinterraerp.db.entities.ContainerData;
import com.cgr.codrinterraerp.db.entities.ReceptionData;
import com.cgr.codrinterraerp.db.relations.FormulaWithVariables;

import java.util.List;

public class ReceptionDataRepository {

    private final MeasurementSystemFormulasDao measurementSystemFormulasDao;
    private final ReceptionTransactionDao receptionTransactionDao;

    private final ReceptionRepository receptionRepository;
    private final DispatchRepository dispatchRepository;

    public ReceptionDataRepository(MeasurementSystemFormulasDao measurementSystemFormulasDao, ReceptionTransactionDao receptionTransactionDao,
                                   ReceptionRepository receptionRepository, DispatchRepository dispatchRepository) {
        this.measurementSystemFormulasDao = measurementSystemFormulasDao;
        this.receptionTransactionDao = receptionTransactionDao;
        this.receptionRepository = receptionRepository;
        this.dispatchRepository = dispatchRepository;
    }

    // ✅ FETCH FORMULA
    public List<FormulaWithVariables> getFormulasWithVariables(int measurementSystemId) {
        return measurementSystemFormulasDao.getFormulasWithVariables(measurementSystemId);
    }

    // ✅ SAVE (TRANSACTION SAFE)
    public boolean saveMeasurementData(ReceptionData receptionData, ContainerData containerData) {

        boolean isSaved = receptionTransactionDao.saveMeasurementData(receptionData, containerData);

        if (isSaved) {
            receptionRepository.updateSummary(receptionData.getReceptionId(), receptionData.getTempReceptionId());
            dispatchRepository.updateSummary(containerData.getDispatchId(), containerData.getTempDispatchId());
        }

        return isSaved;
    }
}