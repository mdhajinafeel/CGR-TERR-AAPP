package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.GirthClassification;

import java.util.List;

@Dao
public interface GirthClassificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGirthClassification(List<GirthClassification> girthClassificationList);

    @Query("SELECT * FROM girth_classification ORDER BY id ASC")
    List<GirthClassification> getAllGirthClassification();

    @Query("DELETE FROM girth_classification")
    void clearAll();
}