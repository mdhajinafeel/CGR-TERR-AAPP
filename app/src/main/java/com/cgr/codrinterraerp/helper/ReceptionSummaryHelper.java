package com.cgr.codrinterraerp.helper;

import com.cgr.codrinterraerp.db.dao.ReceptionDataDao;
import com.cgr.codrinterraerp.db.entities.ReceptionSummary;

import javax.inject.Inject;

public class ReceptionSummaryHelper {

    private final ReceptionDataDao receptionDataDao;

    @Inject
    public ReceptionSummaryHelper(ReceptionDataDao receptionDataDao) {
        this.receptionDataDao = receptionDataDao;
    }

    public ReceptionSummary calculate(Integer receptionId, String tempReceptionId) {

        ReceptionSummary s = new ReceptionSummary();

        if (receptionId != null) {
            // ✅ ONLY use receptionId
            s.receptionId = receptionId;
            s.tempReceptionId = null;

            s.totalPieces = receptionDataDao.sumPiecesByReceptionId(receptionId);
            s.totalGrossVolume = receptionDataDao.sumGrossByReceptionId(receptionId);
            s.totalNetVolume = receptionDataDao.sumNetByReceptionId(receptionId);

        } else {
            // ✅ ONLY use tempReceptionId
            s.receptionId = null;
            s.tempReceptionId = tempReceptionId;

            s.totalPieces = receptionDataDao.sumPiecesByTempReceptionId(tempReceptionId);
            s.totalGrossVolume = receptionDataDao.sumGrossByTempReceptionId(tempReceptionId);
            s.totalNetVolume = receptionDataDao.sumNetByTempReceptionId(tempReceptionId);
        }

        s.updatedAt = System.currentTimeMillis();
        return s;
    }
}