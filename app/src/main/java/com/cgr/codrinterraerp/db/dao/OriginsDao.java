package com.cgr.codrinterraerp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.entities.Origins;

import java.util.List;

@Dao
public interface OriginsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrigins(List<Origins> origins);

    @Query("SELECT * FROM origins ORDER BY originId ASC")
    List<Origins> getAllOrigins();

    @Query("DELETE FROM origins")
    void clearAll();
}