package com.cgr.codrinterraerp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.views.ReceptionView;

import java.util.List;

@Dao
public interface ReceptionViewDao {

    // Optional (recommended)
    @Query("SELECT * FROM reception_view ORDER BY id DESC")
    LiveData<List<ReceptionView>> getReceptionList();
}