package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.ProductTypes;

import java.util.List;

@Dao
public interface ProductTypesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProductTypes(List<ProductTypes> productTypesList);

    @Query("SELECT * FROM product_types ORDER BY typeId ASC")
    List<ProductTypes> getAllProductTypes();

    @Query("DELETE FROM product_types")
    void clearAll();
}