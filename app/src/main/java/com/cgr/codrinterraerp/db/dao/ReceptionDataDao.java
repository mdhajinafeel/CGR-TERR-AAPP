package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.ReceptionData;

import java.util.List;

@Dao
public interface ReceptionDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReceptionData(List<ReceptionData> receptionDataList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertReceptionData(ReceptionData receptionData);

    @Query("SELECT IFNULL(AVG(circumference),0) FROM reception_data WHERE receptionId IN (" +
            "SELECT receptionId FROM container_data WHERE dispatchId = :dispatchId AND isDeleted = 0)")
    double avgGirthByDispatch(Integer dispatchId);

    @Query("SELECT IFNULL(AVG(circumference),0) FROM reception_data WHERE receptionId IN (" +
            "SELECT receptionId FROM container_data WHERE tempDispatchId = :tempDispatchId AND isDeleted = 0)")
    double avgGirthByTempDispatchId(String tempDispatchId);

    @Query("SELECT IFNULL(SUM(pieces),0) FROM reception_data WHERE receptionId = :receptionId AND isDeleted = 0")
    int sumPiecesByReceptionId(Integer receptionId);

    @Query("SELECT IFNULL(SUM(pieces),0) FROM reception_data WHERE tempReceptionId = :tempId AND isDeleted = 0")
    int sumPiecesByTempReceptionId(String tempId);

    @Query("SELECT IFNULL(SUM(grossVolume),0) FROM reception_data WHERE receptionId = :receptionId AND isDeleted = 0")
    double sumGrossByReceptionId(Integer receptionId);

    @Query("SELECT IFNULL(SUM(grossVolume),0) FROM reception_data WHERE tempReceptionId = :tempId AND isDeleted = 0")
    double sumGrossByTempReceptionId(String tempId);

    @Query("SELECT IFNULL(SUM(netVolume),0) FROM reception_data WHERE receptionId = :receptionId AND isDeleted = 0")
    double sumNetByReceptionId(Integer receptionId);

    @Query("SELECT IFNULL(SUM(netVolume),0) FROM reception_data WHERE tempReceptionId = :tempId AND isDeleted = 0")
    double sumNetByTempReceptionId(String tempId);
}