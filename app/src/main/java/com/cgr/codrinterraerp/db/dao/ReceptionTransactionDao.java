package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Transaction;

import com.cgr.codrinterraerp.db.entities.ContainerData;
import com.cgr.codrinterraerp.db.entities.ReceptionData;
import com.cgr.codrinterraerp.utils.AppLogger;

@Dao
public abstract class ReceptionTransactionDao {

    @Insert
    public abstract long insertReceptionData(ReceptionData data);

    @Insert
    public abstract long insertContainerData(ContainerData data);

    @Transaction
    public boolean saveMeasurementData(ReceptionData receptionData, ContainerData containerData) {
        try {
            long receptionId = insertReceptionData(receptionData);
            long containerId = insertContainerData(containerData);

            return receptionId > 0 && containerId > 0;

        } catch (Exception e) {
            AppLogger.e(getClass(), "saveMeasurementData", e);
            return false;
        }
    }
}