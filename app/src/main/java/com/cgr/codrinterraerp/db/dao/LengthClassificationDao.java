package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.LengthClassification;

import java.util.List;

@Dao
public interface LengthClassificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLengthClassification(List<LengthClassification> lengthClassificationList);

    @Query("SELECT * FROM length_classification ORDER BY id ASC")
    List<LengthClassification> getAllLengthClassification();

    @Query("DELETE FROM length_classification")
    void clearAll();
}