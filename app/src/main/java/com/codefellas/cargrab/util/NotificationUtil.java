package com.codefellas.cargrab.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.codefellas.cargrab.R;

public class NotificationUtil {
    public static void createChannels(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("notif_id", "Alerts", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static void createNotification(Context context, String title, String description) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notif_id")
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.drawable.ic_notif_alert);

        manager.notify(0, builder.build());
    }
}