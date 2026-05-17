package com.cgr.codrinterraerp.worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.helper.PreferenceManager;
import com.cgr.codrinterraerp.repository.SyncRepository;
import com.cgr.codrinterraerp.utils.AppLogger;

public class SyncReminderWorker extends Worker {

    private static final String CHANNEL_ID = "sync_reminder_channel";
    private final SyncRepository syncRepository;

    public SyncReminderWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
        syncRepository = new SyncRepository(context);
    }

    @NonNull
    @Override
    public Result doWork() {

        try {

            // =============================
            // BATTERY CHECK
            // =============================

            if (isPowerSaveModeEnabled()) {
                return Result.success();
            }

            // =============================
            // LAST SYNC
            // =============================
            long lastSyncTime = PreferenceManager.INSTANCE.getLastSyncTime();
            long currentTime = System.currentTimeMillis();
            long diffHours = (currentTime - lastSyncTime) / (1000 * 60 * 60);

            // =============================
            // UNSYNCED DATA
            // =============================
            if (!syncRepository.hasUnsyncedData()) {
                return Result.success();
            }

            // =============================
            // PREVENT SPAM
            // =============================
            long lastReminderTime = PreferenceManager.INSTANCE.getLastReminderTime();
            long reminderDiffHours = (currentTime - lastReminderTime) / (1000 * 60 * 60);

            if (reminderDiffHours < 1) {
                return Result.success();
            }

            // =============================
            // MESSAGE
            // =============================
            String title = getApplicationContext().getString(R.string.sync_reminder);

            String message = null;
            boolean silent = true;

            if (diffHours >= 12) {
                message = getApplicationContext().getString(R.string.sync_overdue_please_sync_your_pending_data_immediately);
                silent = false;
            } else if (diffHours >= 6) {
                message = getApplicationContext().getString(R.string.your_data_has_not_been_synced_for_several_hours);
            } else if (diffHours >= 2) {
                message = getApplicationContext().getString(R.string.you_have_pending_data_waiting_for_sync);
            }

            // =============================
            // SHOW
            // =============================
            if (message != null) {
                showNotification(title, message, silent);
                PreferenceManager.INSTANCE.setLastReminderTime(currentTime);
            }

            return Result.success();

        } catch (Exception e) {
            AppLogger.e(getClass(), "SyncReminder", e);
            return Result.failure();
        }
    }

    private boolean isPowerSaveModeEnabled() {
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        return pm != null && pm.isPowerSaveMode();
    }

    private void showNotification(String title, String message, boolean silent) {
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager == null) {
            return;
        }

        // =============================
        // CHANNEL
        // =============================

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, getApplicationContext().getString(R.string.sync_reminder), NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        // =============================
        // NOTIFICATION
        // =============================
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSilent(silent)
                .setAutoCancel(true);

        manager.notify(9999, builder.build());
    }
}