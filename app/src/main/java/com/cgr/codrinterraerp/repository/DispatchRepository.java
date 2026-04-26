package com.cgr.codrinterraerp.repository;

import androidx.lifecycle.LiveData;

import com.cgr.codrinterraerp.db.dao.DispatchContainersDao;
import com.cgr.codrinterraerp.db.dao.DispatchDetailsDao;
import com.cgr.codrinterraerp.db.dao.DispatchViewDao;
import com.cgr.codrinterraerp.db.entities.DispatchContainers;
import com.cgr.codrinterraerp.db.entities.DispatchDetails;
import com.cgr.codrinterraerp.db.views.DispatchView;

import java.util.List;

public class DispatchRepository {

    private final DispatchDetailsDao dispatchDetailsDao;
    private final DispatchContainersDao dispatchContainersDao;
    private final DispatchViewDao dispatchViewDao;

    public DispatchRepository(DispatchDetailsDao dispatchDetailsDao, DispatchContainersDao dispatchContainersDao, DispatchViewDao dispatchViewDao) {
        this.dispatchDetailsDao = dispatchDetailsDao;
        this.dispatchContainersDao = dispatchContainersDao;
        this.dispatchViewDao = dispatchViewDao;
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
}