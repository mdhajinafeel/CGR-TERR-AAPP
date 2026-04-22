package com.cgr.codrinterraerp.repository;

import com.cgr.codrinterraerp.db.dao.ApiLogsDao;
import com.cgr.codrinterraerp.helper.PreferenceManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppMaintenanceRepository {

    private final ApiLogsDao apiLogsDao;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public AppMaintenanceRepository(ApiLogsDao apiLogsDao) {
        this.apiLogsDao = apiLogsDao;
    }

    public void cleanupLogsIfNeeded() {

        long lastCleanup = PreferenceManager.INSTANCE.getLastCleanupTime();
        long now = System.currentTimeMillis();

        if (now - lastCleanup < 24 * 60 * 60 * 1000) return;

        executor.execute(() -> {
            long sevenDaysAgo = now - (7L * 24 * 60 * 60 * 1000);
            apiLogsDao.deleteOldLogs(sevenDaysAgo);

            PreferenceManager.INSTANCE.setLastCleanupTime(now);
        });
    }
}