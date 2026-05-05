package com.cgr.codrinterraerp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.cgr.codrinterraerp.db.CGRTerraERPDatabase;
import com.cgr.codrinterraerp.db.entities.ApiLogs;
import com.cgr.codrinterraerp.utils.AppLogger;
import com.cgr.codrinterraerp.utils.CommonUtils;
import com.cgr.codrinterraerp.worker.LogCleanupWorker;

import java.security.KeyStore;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dagger.hilt.android.HiltAndroidApp;
import devliving.online.securedpreferencestore.DefaultRecoveryHandler;
import devliving.online.securedpreferencestore.SecuredPreferenceStore;

@HiltAndroidApp
public class CodrinTerraErpApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize preferences
        initSecureSharedPref();

        // Log Cleanup
        scheduleLogCleanup(this);

        // Initialize Logger
        CGRTerraERPDatabase db = CGRTerraERPDatabase.getInstance(this);

        AppLogger.init(db.apiLogsDao());

        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {

            StackTraceElement[] stackTrace = throwable.getStackTrace();

            ApiLogs log = new ApiLogs();
            log.type = "CRASH";
            log.errorMessage = throwable.getMessage();

            String stack = Log.getStackTraceString(throwable);

            log.exceptionType = throwable.getClass().getSimpleName();
            log.type = CommonUtils.classifyError(throwable);

            if (stack.length() > 5000) {
                stack = stack.substring(0, 5000) + "...";
            }

            if (stackTrace.length > 0) {
                StackTraceElement element = stackTrace[0];
                log.tag = element.getClassName();       // full class name
                log.methodName = element.getMethodName(); // method name
            }

            log.responseBody = stack;
            log.createdAt = System.currentTimeMillis();
            log.success = false;

            db.apiLogsDao().insertApiLogs(log);
            // Let system crash normally
            System.exit(2);
        });
    }

    private void initSecureSharedPref() {
        try {
            String prefName = "CGR_DIGITAL_TERRA_ERP_PREF";
            String prefix = "cgr_digital";
            byte[] seed = "cgr_digital".getBytes();
            SecuredPreferenceStore.init(getApplicationContext(), prefName, prefix, seed, new DefaultRecoveryHandler());
            SecuredPreferenceStore.setRecoveryHandler(new DefaultRecoveryHandler() {
                @Override
                protected boolean recover(Exception e, KeyStore keyStore, List<String> keyAliases, SharedPreferences preferences) {
                    return super.recover(e, keyStore, keyAliases, preferences);
                }
            });

        } catch (Exception e) {
            AppLogger.e(getClass(), "initSecureSharedPref", e);
        }
    }

    private void scheduleLogCleanup(Context context) {

        Constraints constraints = new Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .build();

        PeriodicWorkRequest work =
                new PeriodicWorkRequest.Builder(LogCleanupWorker.class, 12, TimeUnit.HOURS)
                        .setConstraints(constraints)
                        .setInitialDelay(1, TimeUnit.HOURS) // 🔥 add this
                        .build();

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "log_cleanup",
                ExistingPeriodicWorkPolicy.KEEP,
                work
        );
    }
}