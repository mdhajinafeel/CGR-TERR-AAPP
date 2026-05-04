package com.cgr.codrinterraerp.helper;

import com.cgr.codrinterraerp.db.dao.ReceptionDataDao;
import com.cgr.codrinterraerp.db.entities.ReceptionSummary;
import com.cgr.codrinterraerp.utils.CommonUtils;

import javax.inject.Inject;

public class ReceptionSummaryHelper {

    private final ReceptionDataDao receptionDataDao;

    @Inject
    public ReceptionSummaryHelper(ReceptionDataDao receptionDataDao) {
        this.receptionDataDao = receptionDataDao;
    }

    public ReceptionSummary calculate(Integer receptionId, String tempReceptionId) {

        ReceptionSummary s = new ReceptionSummary();

        if (receptionId != null && receptionId > 0) {
            // ✅ ONLY use receptionId
            s.receptionId = receptionId;
            s.tempReceptionId = null;

            s.totalPieces = receptionDataDao.sumPiecesByReceptionId(receptionId);
            s.totalGrossVolume = CommonUtils.round(receptionDataDao.sumGrossByReceptionId(receptionId), 3);
            s.totalNetVolume = CommonUtils.round(receptionDataDao.sumNetByReceptionId(receptionId), 3);
            s.totalVolumePie = CommonUtils.round(receptionDataDao.sumPieByReceptionId(receptionId), 3);
        } else {
            // ✅ ONLY use tempReceptionId
            s.receptionId = null;
            s.tempReceptionId = tempReceptionId;

            s.totalPieces = receptionDataDao.sumPiecesByTempReceptionId(tempReceptionId);
            s.totalGrossVolume = CommonUtils.round(receptionDataDao.sumGrossByTempReceptionId(tempReceptionId), 3);
            s.totalNetVolume = CommonUtils.round(receptionDataDao.sumNetByTempReceptionId(tempReceptionId), 3);
            s.totalVolumePie = CommonUtils.round(receptionDataDao.sumPieByTempReceptionId(tempReceptionId), 3);
        }

        s.updatedAt = System.currentTimeMillis();
        return s;
    }
}