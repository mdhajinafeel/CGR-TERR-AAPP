package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.ShippingLines;

import java.util.List;

@Dao
public interface ShippingLinesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShippingLines(List<ShippingLines> shippingLinesList);

    @Query("SELECT * FROM shipping_lines ORDER BY id ASC")
    List<ShippingLines> getAllShippingLines();

    @Query("DELETE FROM shipping_lines")
    void clearAll();
}