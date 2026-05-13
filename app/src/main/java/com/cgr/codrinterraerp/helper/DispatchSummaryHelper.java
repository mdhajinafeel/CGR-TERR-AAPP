package com.cgr.codrinterraerp.helper;

import com.cgr.codrinterraerp.db.dao.ContainerDataDao;
import com.cgr.codrinterraerp.db.dao.ReceptionDataDao;
import com.cgr.codrinterraerp.db.entities.DispatchSummary;
import com.cgr.codrinterraerp.utils.CommonUtils;

import javax.inject.Inject;

public class DispatchSummaryHelper {

    private final ContainerDataDao containerDataDao;
    private final ReceptionDataDao receptionDataDao;

    @Inject
    public DispatchSummaryHelper(ContainerDataDao containerDataDao, ReceptionDataDao receptionDataDao) {
        this.containerDataDao = containerDataDao;
        this.receptionDataDao = receptionDataDao;
    }

    public DispatchSummary calculate(String tempDispatchId) {

        DispatchSummary s = new DispatchSummary();

        // ✅ ONLY use tempDispatchId
        s.tempDispatchId = tempDispatchId;

        s.totalPieces = containerDataDao.sumPiecesByTempDispatchId(tempDispatchId);
        s.totalGrossVolume = CommonUtils.round(containerDataDao.sumGrossByTempDispatchId(tempDispatchId), 3);
        s.totalNetVolume = CommonUtils.round(containerDataDao.sumNetByTempDispatchId(tempDispatchId), 3);
        s.avgGirth = CommonUtils.round(receptionDataDao.avgGirthByTempDispatchId(tempDispatchId), 2);
        s.totalVolumePie = CommonUtils.round(containerDataDao.sumPieByTempDispatchId(tempDispatchId), 2);

        if (s.totalNetVolume > 0 && s.totalPieces > 0) {
            double cftValue = (s.totalNetVolume / s.totalPieces) * 35.315;
            if (cftValue > 0) {
                s.cft = CommonUtils.round(cftValue, 2);
            } else {
                s.cft = 0;
            }
        } else {
            s.cft = 0;
        }

        s.updatedAt = System.currentTimeMillis();
        return s;
    }
}