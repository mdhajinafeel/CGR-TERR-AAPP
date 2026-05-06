package com.cgr.codrinterraerp.repository;

import androidx.lifecycle.LiveData;

import com.cgr.codrinterraerp.db.dao.ApiLogsDao;
import com.cgr.codrinterraerp.db.entities.ApiLogs;
import com.cgr.codrinterraerp.model.LogCount;

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

    public LiveData<List<ApiLogs>> getApiLogsByType(String type) {
        return apiLogsDao.getApiLogsByType(type);
    }

    public void clearLogsByType(String type) {

        if ("ALL".equalsIgnoreCase(type)) {
            executor.execute(apiLogsDao::clearAll);
        } else {
            executor.execute(() ->
                    apiLogsDao.clearLogsByType(type)
            );
        }
    }

    public void clearLog(int id, long createdAt) {

        executor.execute(() ->
                apiLogsDao.clearLog(id, createdAt)
        );
    }

    public LiveData<List<LogCount>> getLogCounts() {
        return apiLogsDao.getLogCounts();
    }
}