package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.PurchaseContracts;

import java.util.List;

@Dao
public interface PurchaseContractDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPurchaseContracts(List<PurchaseContracts> purchaseContractsList);

    @Query("SELECT * FROM purchase_contracts WHERE supplierId = :supplierId AND product = :product AND productType IN(:productTypeList) ORDER BY contractId ASC")
    List<PurchaseContracts> getPurchaseContracts(int supplierId, int product, List<Integer> productTypeList);

    @Query("DELETE FROM purchase_contracts")
    void clearAll();
}