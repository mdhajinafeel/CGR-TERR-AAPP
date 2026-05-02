package com.cgr.codrinterraerp.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Transaction;

import com.cgr.codrinterraerp.db.dao.ContainerDataDao;
import com.cgr.codrinterraerp.db.dao.DispatchContainersDao;
import com.cgr.codrinterraerp.db.dao.DispatchDetailsDao;
import com.cgr.codrinterraerp.db.dao.DispatchSummaryDao;
import com.cgr.codrinterraerp.db.dao.DispatchViewDao;
import com.cgr.codrinterraerp.db.dao.ReceptionDataDao;
import com.cgr.codrinterraerp.db.dao.ReceptionSummaryDao;
import com.cgr.codrinterraerp.db.entities.DispatchContainers;
import com.cgr.codrinterraerp.db.entities.DispatchDetails;
import com.cgr.codrinterraerp.db.entities.DispatchSummary;
import com.cgr.codrinterraerp.db.entities.ReceptionSummary;
import com.cgr.codrinterraerp.db.views.DispatchView;
import com.cgr.codrinterraerp.helper.DispatchSummaryHelper;
import com.cgr.codrinterraerp.helper.ReceptionSummaryHelper;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DispatchRepository {

    private final DispatchDetailsDao dispatchDetailsDao;
    private final DispatchContainersDao dispatchContainersDao;
    private final DispatchViewDao dispatchViewDao;
    private final DispatchSummaryDao dispatchSummaryDao;
    private final ReceptionSummaryDao receptionSummaryDao;
    private final DispatchSummaryHelper dispatchSummaryHelper;
    private final ReceptionSummaryHelper receptionSummaryHelper;
    private final ContainerDataDao containerDataDao;
    private final ReceptionDataDao receptionDataDao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public DispatchRepository(DispatchDetailsDao dispatchDetailsDao, DispatchContainersDao dispatchContainersDao, DispatchViewDao dispatchViewDao,
                              DispatchSummaryDao dispatchSummaryDao, DispatchSummaryHelper dispatchSummaryHelper, ContainerDataDao containerDataDao,
                              ReceptionDataDao receptionDataDao, ReceptionSummaryHelper receptionSummaryHelper, ReceptionSummaryDao receptionSummaryDao) {
        this.dispatchDetailsDao = dispatchDetailsDao;
        this.dispatchContainersDao = dispatchContainersDao;
        this.dispatchViewDao = dispatchViewDao;
        this.dispatchSummaryDao = dispatchSummaryDao;
        this.dispatchSummaryHelper = dispatchSummaryHelper;
        this.containerDataDao = containerDataDao;
        this.receptionDataDao = receptionDataDao;
        this.receptionSummaryHelper = receptionSummaryHelper;
        this.receptionSummaryDao = receptionSummaryDao;
    }

    public long saveDispatchDetails(DispatchDetails dispatchDetails) {
        return dispatchDetailsDao.insertOrUpdateDispatchDetails(dispatchDetails);
    }

    public int getDispatchContainersCount(String containerNumber, int shippingLineId) {
        return dispatchContainersDao.getDispatchContainersCount(containerNumber, shippingLineId);
    }

    public LiveData<List<DispatchView>> getDispatchList() {
        return dispatchViewDao.getDispatchList();
    }

    public void insertDispatchContainer(DispatchContainers dispatchContainer) {
        dispatchContainersDao.insertDispatchContainer(dispatchContainer);
    }

    public void updateSummary(Integer dispatchId, String tempDispatchId) {
        executor.execute(() -> {
            DispatchSummary s = dispatchSummaryHelper.calculate(dispatchId, tempDispatchId);
            dispatchSummaryDao.upsert(s);
        });
    }

    public void updateReceptionSummary(List<String> receptionIds) {
        executor.execute(() -> {

            for (String receptionId : receptionIds) {
                ReceptionSummary s = receptionSummaryHelper.calculate(null, receptionId);
                receptionSummaryDao.upsert(s);
            }
        });
    }

    public LiveData<List<DispatchView>> getAvailableContainers() {
        return dispatchViewDao.getAvailableContainers();
    }

    public DispatchDetails fetchDispatchDetailById(String tempDispatchId) {
        return dispatchDetailsDao.fetchDispatchDetailById(tempDispatchId);
    }

    public int getDispatchContainersCountForEdit(String containerNumber, int shippingLineId, String tempDispatchId) {
        return dispatchDetailsDao.getDispatchContainersCountForEdit(containerNumber, shippingLineId, tempDispatchId);
    }

    public void deleteDispatchContainers(String containerNumber, int shippingLineId) {
        dispatchContainersDao.deleteDispatchContainers(containerNumber, shippingLineId);
    }

    @Transaction
    public int deleteFullDispatch(String tempDispatchId, long updatedAt) {

        int total = 0;

        // 1️⃣ Get related reception IDs
        List<String> receptionDataIds = containerDataDao.getReceptionDataIdsByDispatch(tempDispatchId);

        // 2️⃣ Delete container data
        total += containerDataDao.deleteByTempDispatchId(tempDispatchId, updatedAt);

        // 3️⃣ Delete reception data (if exists)
        if (receptionDataIds != null && !receptionDataIds.isEmpty()) {
            total += receptionDataDao.deleteByReceptionDataIds(receptionDataIds, updatedAt);
        }

        // 4️⃣ Delete dispatch
        total += dispatchDetailsDao.deleteDispatch(tempDispatchId, updatedAt);

        return total;
    }

    public List<String> getAllReceptionIds(String tempDispatchId) {
        return containerDataDao.getAllReceptionIds(tempDispatchId);
    }
}