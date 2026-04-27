package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.cgr.codrinterraerp.db.entities.ContainerData;

import java.util.List;

@Dao
public interface ContainerDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertContainerData(List<ContainerData> containerDataList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertContainerData(ContainerData receptionData);
}