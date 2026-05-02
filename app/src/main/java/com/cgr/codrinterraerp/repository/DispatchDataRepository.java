package com.cgr.codrinterraerp.repository;

import com.cgr.codrinterraerp.db.dao.ContainerDataDao;
import com.cgr.codrinterraerp.model.ContainerWithReception;

import java.util.List;

public class DispatchDataRepository {

    private final ContainerDataDao containerDataDao;

    public DispatchDataRepository(ContainerDataDao containerDataDao) {
        this.containerDataDao = containerDataDao;
    }

    public List<ContainerWithReception> fetchContainerData(Integer dispatchId, String tempDispatchId) {
        if (dispatchId != null && dispatchId > 0) {
            return containerDataDao.fetchByDispatchId(dispatchId);
        } else {
            return containerDataDao.fetchByTempDispatchId(tempDispatchId);
        }
    }
}