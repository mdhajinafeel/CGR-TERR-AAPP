package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.SupplierProductTypes;

import java.util.List;

@Dao
public interface SupplierProductTypesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSupplierProductTypes(List<SupplierProductTypes> supplierProductTypesList);

    @Query("SELECT * FROM supplier_product_types WHERE supplierId = :supplerId AND productId = :productId ORDER BY productTypeId ASC")
    List<SupplierProductTypes> getSupplierProductTypes(int supplerId, int productId);

    @Query("DELETE FROM supplier_product_types")
    void clearAll();
}