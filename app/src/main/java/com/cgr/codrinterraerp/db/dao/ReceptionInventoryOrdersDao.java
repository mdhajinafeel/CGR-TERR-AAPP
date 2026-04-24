package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.ReceptionInventoryOrders;

import java.util.List;

@Dao
public interface ReceptionInventoryOrdersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReceptionInventoryOrders(List<ReceptionInventoryOrders> receptionInventoryOrdersList);

    @Query("SELECT COUNT(*) FROM reception_inventory_orders " +
            "WHERE REPLACE(REPLACE(inventoryOrder, '-', ''), ' ', '') = " +
            "REPLACE(REPLACE(:inventoryOrder, '-', ''), ' ', '') " +
            "AND supplierId = :supplierId")
    int getReceptionInventoryOrdersCount(String inventoryOrder, int supplierId);

    @Query("DELETE FROM reception_inventory_orders")
    void clearAll();
}