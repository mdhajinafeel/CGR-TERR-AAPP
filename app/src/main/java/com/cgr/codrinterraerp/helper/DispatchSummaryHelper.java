package com.cgr.codrinterraerp.helper;

import com.cgr.codrinterraerp.db.dao.ContainerDataDao;
import com.cgr.codrinterraerp.db.dao.ReceptionDataDao;
import com.cgr.codrinterraerp.db.entities.DispatchSummary;

import javax.inject.Inject;

public class DispatchSummaryHelper {

    private final ContainerDataDao containerDataDao;
    private final ReceptionDataDao receptionDataDao;

    @Inject
    public DispatchSummaryHelper(ContainerDataDao containerDataDao, ReceptionDataDao receptionDataDao) {
        this.containerDataDao = containerDataDao;
        this.receptionDataDao = receptionDataDao;
    }

    public DispatchSummary calculate(Integer dispatchId, String tempDispatchId) {

        DispatchSummary s = new DispatchSummary();

        if (dispatchId != null) {
            // ✅ ONLY use dispatchId
            s.dispatchId = dispatchId;
            s.tempDispatchId = null;

            s.totalPieces = containerDataDao.sumPiecesByDispatchId(dispatchId);
            s.totalGrossVolume = containerDataDao.sumGrossByDispatchId(dispatchId);
            s.totalNetVolume = containerDataDao.sumNetByDispatchId(dispatchId);
            s.avgGirth = receptionDataDao.avgGirthByDispatch(dispatchId);

        } else {
            // ✅ ONLY use tempDispatchId
            s.dispatchId = null;
            s.tempDispatchId = tempDispatchId;

            s.totalPieces = containerDataDao.sumPiecesByTempDispatchId(tempDispatchId);
            s.totalGrossVolume = containerDataDao.sumGrossByTempDispatchId(tempDispatchId);
            s.totalNetVolume = containerDataDao.sumNetByTempDispatchId(tempDispatchId);
            s.avgGirth = receptionDataDao.avgGirthByTempDispatchId(tempDispatchId);
        }

        s.updatedAt = System.currentTimeMillis();
        return s;
    }
}