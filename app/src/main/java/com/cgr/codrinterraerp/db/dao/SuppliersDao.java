package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.Suppliers;

import java.util.List;

@Dao
public interface SuppliersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSuppliers(List<Suppliers> suppliersList);

    @Query("SELECT * FROM suppliers ORDER BY supplierId ASC")
    List<Suppliers> getAllSuppliers();

    @Query("DELETE FROM suppliers")
    void clearAll();
}