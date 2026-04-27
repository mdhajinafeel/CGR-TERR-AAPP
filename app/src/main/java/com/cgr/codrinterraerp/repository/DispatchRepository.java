package com.cgr.codrinterraerp.repository;

import androidx.lifecycle.LiveData;

import com.cgr.codrinterraerp.db.dao.DispatchContainersDao;
import com.cgr.codrinterraerp.db.dao.DispatchDetailsDao;
import com.cgr.codrinterraerp.db.dao.DispatchSummaryDao;
import com.cgr.codrinterraerp.db.dao.DispatchViewDao;
import com.cgr.codrinterraerp.db.entities.DispatchContainers;
import com.cgr.codrinterraerp.db.entities.DispatchDetails;
import com.cgr.codrinterraerp.db.entities.DispatchSummary;
import com.cgr.codrinterraerp.db.views.DispatchView;
import com.cgr.codrinterraerp.helper.DispatchSummaryHelper;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DispatchRepository {

    private final DispatchDetailsDao dispatchDetailsDao;
    private final DispatchContainersDao dispatchContainersDao;
    private final DispatchViewDao dispatchViewDao;
    private final DispatchSummaryDao dispatchSummaryDao;
    private final DispatchSummaryHelper dispatchSummaryHelper;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public DispatchRepository(DispatchDetailsDao dispatchDetailsDao, DispatchContainersDao dispatchContainersDao, DispatchViewDao dispatchViewDao,
                              DispatchSummaryDao dispatchSummaryDao, DispatchSummaryHelper dispatchSummaryHelper) {
        this.dispatchDetailsDao = dispatchDetailsDao;
        this.dispatchContainersDao = dispatchContainersDao;
        this.dispatchViewDao = dispatchViewDao;
        this.dispatchSummaryDao = dispatchSummaryDao;
        this.dispatchSummaryHelper = dispatchSummaryHelper;
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

    public LiveData<List<DispatchView>> getAvailableContainers() {
        return dispatchViewDao.getAvailableContainers();
    }
}