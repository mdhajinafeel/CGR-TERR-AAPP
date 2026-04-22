package com.cgr.codrinterraerp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.cgr.codrinterraerp.utils.AppLogger;
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