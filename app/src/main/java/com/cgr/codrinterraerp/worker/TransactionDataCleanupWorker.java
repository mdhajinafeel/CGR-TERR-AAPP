package com.cgr.codrinterraerp.worker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.cgr.codrinterraerp.db.CGRTerraERPDatabase;
import com.cgr.codrinterraerp.db.dao.ReceptionDetailsDao;
import com.cgr.codrinterraerp.utils.AppLogger;

public class TransactionDataCleanupWorker extends Worker {

    private static final String TAG = "TransactionDataCleanupWorker";
    private final ReceptionDetailsDao receptionDetailsDao;
    private final CGRTerraERPDatabase database;

    public TransactionDataCleanupWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);

        // 🔥 Initialize DB
        database = CGRTerraERPDatabase.getInstance(context);
        receptionDetailsDao = database.receptionDetailsDao();
    }

    @NonNull
    @Override
    public Result doWork() {

        try {
            AppLogger.d(getClass(), "Log cleanup started");

            long now = System.currentTimeMillis();
            long threeMonthsAgo = System.currentTimeMillis() - (90L * 24 * 60 * 60 * 1000);

            // 🔥 Run inside transaction (safe + fast)
            database.runInTransaction(() -> {
                receptionDetailsDao.deleteOldData(threeMonthsAgo);
            });

            AppLogger.d(getClass(), "Transaction data cleanup completed");
            return Result.success();

        } catch (Exception e) {
            AppLogger.d(getClass(), "Transaction data cleanup failed");
            return Result.retry();
        }
    }
}