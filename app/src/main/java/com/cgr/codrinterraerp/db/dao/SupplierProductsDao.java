package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.SupplierProducts;

import java.util.List;

@Dao
public interface SupplierProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSupplierProducts(List<SupplierProducts> supplierProductsList);

    @Query("SELECT * FROM supplier_products WHERE supplierId = :supplierId ORDER BY productId ASC")
    List<SupplierProducts> getSupplierProducts(int supplierId);

    @Query("DELETE FROM supplier_products")
    void clearAll();
}