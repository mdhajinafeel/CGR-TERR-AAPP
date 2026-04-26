package com.cgr.codrinterraerp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.cgr.codrinterraerp.db.views.DispatchView;

import java.util.List;

@Dao
public interface DispatchViewDao {

    // Optional (recommended)
    @Query("SELECT * FROM dispatch_view ORDER BY id DESC")
    LiveData<List<DispatchView>> getDispatchList();
}