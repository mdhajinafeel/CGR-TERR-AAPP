package com.cgr.codrinterraerp.repository;

import androidx.lifecycle.LiveData;

import com.cgr.codrinterraerp.db.dao.FarmInventoryOrdersDao;
import com.cgr.codrinterraerp.db.dao.ReceptionDetailsDao;
import com.cgr.codrinterraerp.db.dao.ReceptionInventoryOrdersDao;
import com.cgr.codrinterraerp.db.dao.ReceptionViewDao;
import com.cgr.codrinterraerp.db.entities.FarmInventoryOrders;
import com.cgr.codrinterraerp.db.entities.ReceptionDetails;
import com.cgr.codrinterraerp.db.entities.ReceptionInventoryOrders;
import com.cgr.codrinterraerp.db.views.ReceptionView;

import java.util.List;

public class ReceptionRepository {

    private final ReceptionDetailsDao receptionDetailsDao;
    private final ReceptionInventoryOrdersDao receptionInventoryOrdersDao;
    private final FarmInventoryOrdersDao farmInventoryOrdersDao;
    private final ReceptionViewDao receptionViewDao;

    public ReceptionRepository(ReceptionDetailsDao receptionDetailsDao, ReceptionInventoryOrdersDao receptionInventoryOrdersDao, ReceptionViewDao receptionViewDao,
                               FarmInventoryOrdersDao farmInventoryOrdersDao) {
        this.receptionDetailsDao = receptionDetailsDao;
        this.receptionInventoryOrdersDao = receptionInventoryOrdersDao;
        this.receptionViewDao = receptionViewDao;
        this.farmInventoryOrdersDao = farmInventoryOrdersDao;
    }

    public long saveReceptionDetails(ReceptionDetails receptionDetails) {
       return receptionDetailsDao.insertOrUpdateReceptionDetails(receptionDetails);
    }

    public int getReceptionInventoryOrdersCount(String inventoryOrder, int supplierId) {
        return receptionInventoryOrdersDao.getReceptionInventoryOrdersCount(inventoryOrder, supplierId);
    }

    public LiveData<List<ReceptionView>> getReceptionList() {
        return receptionViewDao.getReceptionList();
    }

    public void insertReceptionInventoryOrder(ReceptionInventoryOrders receptionInventoryOrder) {
        receptionInventoryOrdersDao.insertReceptionInventoryOrder(receptionInventoryOrder);
    }

    public void insertFarmInventoryOrder(FarmInventoryOrders farmInventoryOrder) {
        farmInventoryOrdersDao.insertFarmInventoryOrder(farmInventoryOrder);
    }
}