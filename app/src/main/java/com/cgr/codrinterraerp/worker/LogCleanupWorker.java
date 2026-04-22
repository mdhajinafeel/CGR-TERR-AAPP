package com.cgr.codrinterraerp.worker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.cgr.codrinterraerp.db.CGRTerraERPDatabase;
import com.cgr.codrinterraerp.db.dao.ApiLogsDao;
import com.cgr.codrinterraerp.utils.AppLogger;

public class LogCleanupWorker extends Worker {

    private static final String TAG = "LogCleanupWorker";

    private final ApiLogsDao apiLogsDao;
    private final CGRTerraERPDatabase database;

    public LogCleanupWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);

        // 🔥 Initialize DB
        database = CGRTerraERPDatabase.getInstance(context);
        apiLogsDao = database.apiLogsDao();
    }

    @NonNull
    @Override
    public Result doWork() {

        try {
            AppLogger.d(getClass(), "Log cleanup started");

            long now = System.currentTimeMillis();
            long sevenDaysAgo = now - (7L * 24 * 60 * 60 * 1000);

            // 🔥 Run inside transaction (safe + fast)
            database.runInTransaction(() -> {
                apiLogsDao.deleteOldLogs(sevenDaysAgo);
                apiLogsDao.keepLast500(); // optional safety limit
            });

            AppLogger.d(getClass(), "Log cleanup completed");

            return Result.success();

        } catch (Exception e) {
            AppLogger.d(getClass(), "Log cleanup failed");
            return Result.retry();
        }
    }
}