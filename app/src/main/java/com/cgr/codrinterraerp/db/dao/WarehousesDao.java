package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.Warehouses;

import java.util.List;

@Dao
public interface WarehousesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWarehouses(List<Warehouses> warehousesList);

    @Query("SELECT * FROM warehouses ORDER BY id ASC")
    List<Warehouses> getAllWarehouses();

    @Query("DELETE FROM warehouses")
    void clearAll();
}