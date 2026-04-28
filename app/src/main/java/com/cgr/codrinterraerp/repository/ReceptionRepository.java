package com.cgr.codrinterraerp.repository;

import androidx.lifecycle.LiveData;

import com.cgr.codrinterraerp.db.dao.FarmInventoryOrdersDao;
import com.cgr.codrinterraerp.db.dao.ReceptionDetailsDao;
import com.cgr.codrinterraerp.db.dao.ReceptionInventoryOrdersDao;
import com.cgr.codrinterraerp.db.dao.ReceptionSummaryDao;
import com.cgr.codrinterraerp.db.dao.ReceptionViewDao;
import com.cgr.codrinterraerp.db.entities.FarmInventoryOrders;
import com.cgr.codrinterraerp.db.entities.ReceptionDetails;
import com.cgr.codrinterraerp.db.entities.ReceptionInventoryOrders;
import com.cgr.codrinterraerp.db.entities.ReceptionSummary;
import com.cgr.codrinterraerp.db.views.ReceptionView;
import com.cgr.codrinterraerp.helper.ReceptionSummaryHelper;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReceptionRepository {

    private final ReceptionDetailsDao receptionDetailsDao;
    private final ReceptionInventoryOrdersDao receptionInventoryOrdersDao;
    private final FarmInventoryOrdersDao farmInventoryOrdersDao;
    private final ReceptionViewDao receptionViewDao;
    private final ReceptionSummaryDao receptionSummaryDao;
    private final ReceptionSummaryHelper receptionSummaryHelper;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public ReceptionRepository(ReceptionDetailsDao receptionDetailsDao, ReceptionInventoryOrdersDao receptionInventoryOrdersDao, ReceptionViewDao receptionViewDao,
                               FarmInventoryOrdersDao farmInventoryOrdersDao, ReceptionSummaryDao receptionSummaryDao, ReceptionSummaryHelper receptionSummaryHelper) {
        this.receptionDetailsDao = receptionDetailsDao;
        this.receptionInventoryOrdersDao = receptionInventoryOrdersDao;
        this.receptionViewDao = receptionViewDao;
        this.farmInventoryOrdersDao = farmInventoryOrdersDao;
        this.receptionSummaryDao = receptionSummaryDao;
        this.receptionSummaryHelper = receptionSummaryHelper;
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

    public void updateSummary(Integer receptionId, String tempReceptionId) {
        executor.execute(() -> {
            ReceptionSummary s = receptionSummaryHelper.calculate(receptionId, tempReceptionId);
            receptionSummaryDao.upsert(s);
        });
    }
}