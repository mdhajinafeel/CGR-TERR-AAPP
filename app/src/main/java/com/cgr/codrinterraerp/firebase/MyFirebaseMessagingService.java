package com.cgr.codrinterraerp.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.helper.PreferenceManager;
import com.cgr.codrinterraerp.ui.activities.MainActivity;
import com.cgr.codrinterraerp.utils.AppLogger;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseService";
    private static final String CHANNEL_ID = "fcm_default_channel";  // Consistent channel ID

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        AppLogger.d(getClass(), "New FCM Token: " + token);
        // Save token for later use, such as sending notifications from your server
        PreferenceManager.INSTANCE.setFirebaseToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        AppLogger.d(getClass(), "Message received: " + remoteMessage.getData());

        if (!remoteMessage.getData().isEmpty()) {
            // If the message contains data payload
            sendNotification(remoteMessage.getData());
        }
    }

    private void sendNotification(Map<String, String> data) {
        String notificationTitle = "";
        String notificationContent = "";
        String notificationType = "";

        // Check if data contains necessary fields
        if (data.containsKey("title") && data.containsKey("body") && data.containsKey("type")) {
            notificationTitle = data.get("title");
            notificationContent = data.get("body");
            notificationType = data.get("type");
        }

        // Create the intent to open the DashboardActivity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("IsNotificationClicked", true);
        intent.putExtra("NotificationType", notificationType);

        // Create PendingIntent to launch activity on notification click
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                (int) System.currentTimeMillis(),
                intent,
                PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE
        );

        // Build the notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(notificationTitle)
                .setContentText(notificationContent)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        // Get system NotificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Create Notification Channel (for Android O and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "FCM Notifications",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription(notificationContent);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        // Show the notification
        if (notificationManager != null) {
            notificationManager.notify((int) System.currentTimeMillis(), notificationBuilder.build());
        }
    }
}