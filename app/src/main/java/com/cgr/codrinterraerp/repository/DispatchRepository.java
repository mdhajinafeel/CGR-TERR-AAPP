package com.cgr.codrinterraerp.repository;

import com.cgr.codrinterraerp.db.dao.DispatchContainersDao;
import com.cgr.codrinterraerp.db.dao.DispatchDetailsDao;
import com.cgr.codrinterraerp.db.entities.DispatchDetails;

public class DispatchRepository {

    private final DispatchDetailsDao dispatchDetailsDao;
    private final DispatchContainersDao dispatchContainersDao;

    public DispatchRepository(DispatchDetailsDao dispatchDetailsDao, DispatchContainersDao dispatchContainersDao) {
        this.dispatchDetailsDao = dispatchDetailsDao;
        this.dispatchContainersDao = dispatchContainersDao;
    }

    public long saveDispatchDetails(DispatchDetails dispatchDetails) {
       return dispatchDetailsDao.insertOrUpdateDispatchDetails(dispatchDetails);
    }

    public int getDispatchContainersCount(String containerNumber, int shippingLineId) {
        return dispatchContainersDao.getDispatchContainersCount(containerNumber, shippingLineId);
    }
}