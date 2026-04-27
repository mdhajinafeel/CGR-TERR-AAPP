package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.ContainerData;

import java.util.List;

@Dao
public interface ContainerDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertContainerData(List<ContainerData> containerDataList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertContainerData(ContainerData receptionData);

    @Query("SELECT IFNULL(SUM(pieces),0) FROM container_data WHERE dispatchId = :dispatchId AND isDeleted = 0")
    int sumPiecesByDispatchId(Integer dispatchId);

    @Query("SELECT IFNULL(SUM(pieces),0) FROM container_data WHERE tempDispatchId = :tempId AND isDeleted = 0")
    int sumPiecesByTempDispatchId(String tempId);

    @Query("SELECT IFNULL(SUM(grossVolume),0) FROM container_data WHERE dispatchId = :dispatchId AND isDeleted = 0")
    double sumGrossByDispatchId(Integer dispatchId);

    @Query("SELECT IFNULL(SUM(grossVolume),0) FROM container_data WHERE tempDispatchId = :tempId AND isDeleted = 0")
    double sumGrossByTempDispatchId(String tempId);

    @Query("SELECT IFNULL(SUM(netVolume),0) FROM container_data WHERE dispatchId = :dispatchId AND isDeleted = 0")
    double sumNetByDispatchId(Integer dispatchId);

    @Query("SELECT IFNULL(SUM(netVolume),0) FROM container_data WHERE tempDispatchId = :tempId AND isDeleted = 0")
    double sumNetByTempDispatchId(String tempId);
}