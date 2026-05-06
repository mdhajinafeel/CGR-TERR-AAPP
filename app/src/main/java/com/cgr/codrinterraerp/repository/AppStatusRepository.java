package com.cgr.codrinterraerp.repository;

import androidx.lifecycle.LiveData;

import com.cgr.codrinterraerp.db.dao.ApiLogsDao;
import com.cgr.codrinterraerp.db.entities.ApiLogs;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppStatusRepository {

    private final ApiLogsDao apiLogsDao;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public AppStatusRepository(ApiLogsDao apiLogsDao) {
        this.apiLogsDao = apiLogsDao;
    }

    public LiveData<List<ApiLogs>> getAllApiLogs() {
        return apiLogsDao.getAllApiLogs();
    }
}