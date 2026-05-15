package com.cgr.codrinterraerp.firebase;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.cgr.codrinterraerp.BuildConfig;
import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.db.CGRTerraERPDatabase;
import com.cgr.codrinterraerp.db.entities.PushNotifications;
import com.cgr.codrinterraerp.helper.PreferenceManager;
import com.cgr.codrinterraerp.ui.activities.MainActivity;
import com.cgr.codrinterraerp.ui.activities.SplashActivity;
import com.cgr.codrinterraerp.utils.CommonUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.concurrent.Executors;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "fcm_default_channel";

    @Override
    public void onCreate() {
        super.onCreate();

        // =====================================
        // CREATE CHANNEL ONCE
        // =====================================
        createNotificationChannel();
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        PreferenceManager.INSTANCE.setFirebaseToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (!remoteMessage.getData().isEmpty()) {
            sendNotification(remoteMessage.getData());
        }
    }

    private void sendNotification(Map<String, String> data) {
        String notificationTitle = "";
        String notificationContent = "";
        String notificationType = "";
        String notificationStatus = "";

        // =====================================
        // READ PAYLOAD
        // =====================================
        if (data.containsKey("title") && data.containsKey("body") && data.containsKey("type") && data.containsKey("status")) {
            notificationTitle = data.get("title");
            notificationContent = data.get("body");
            notificationType = data.get("type");
            notificationStatus = data.get("status");
        }

        // =====================================
        // SAVE LOCALLY
        // =====================================
        saveNotificationLocally(notificationTitle, notificationContent, notificationType, notificationStatus);

        // =====================================
        // INTENT
        // =====================================
        Intent intent = getIntent(notificationType);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent,
                PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

        // =====================================
        // NOTIFICATION
        // =====================================
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(CommonUtils.getLocalizedString(getApplicationContext(), notificationTitle))
                .setContentText(CommonUtils.getLocalizedString(getApplicationContext(), notificationContent))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        // =====================================
        // SHOW
        // =====================================
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            notificationManager.notify((int) System.currentTimeMillis(), notificationBuilder.build());
        }
    }

    @NonNull
    private Intent getIntent(String notificationType) {
        Intent intent;

        if (isAppInForeground()) {
            // =====================================
            // APP OPEN
            // =====================================
            intent = new Intent(this, MainActivity.class);
        } else {
            // =====================================
            // APP CLOSED/KILLED
            // =====================================
            intent = new Intent(this, SplashActivity.class);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("IsNotificationClicked", true);
        intent.putExtra("NotificationType", notificationType);
        return intent;
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager == null) {
                return;
            }

            NotificationChannel existing = manager.getNotificationChannel(CHANNEL_ID);

            // =====================================
            // ALREADY EXISTS
            // =====================================
            if (existing != null) {
                return;
            }

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "FCM Notifications", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(BuildConfig.APPLICATION_NAME);
            manager.createNotificationChannel(channel);
        }
    }

    private void saveNotificationLocally(String title, String body, String type, String status) {

        Executors.newSingleThreadExecutor()
                .execute(() -> {
                    PushNotifications entity = new PushNotifications();
                    entity.title = title;
                    entity.message = body;
                    entity.type = type;
                    entity.status = status;
                    CGRTerraERPDatabase.getInstance(this).pushNotificationsDao().insert(entity);
                });
    }

    private boolean isAppInForeground() {
        ActivityManager.RunningAppProcessInfo appProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(appProcessInfo);
        return appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND ||
                appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE;
    }
}