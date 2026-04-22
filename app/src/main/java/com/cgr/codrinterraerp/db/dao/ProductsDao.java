package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.Products;

import java.util.List;

@Dao
public interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProducts(List<Products> productsList);

    @Query("SELECT * FROM products ORDER BY productId ASC")
    List<Products> getAllProducts();

    @Query("DELETE FROM products")
    void clearAll();
}