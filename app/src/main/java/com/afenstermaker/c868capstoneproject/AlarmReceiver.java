package com.afenstermaker.c868capstoneproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private static int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        createNotificationChannel(context);
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String message = intent.getStringExtra("message");
        deliverNotification(context, message);
    }

    private void deliverNotification(Context context, String message) {
        Intent contentIntent = new Intent(context, MainActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
                .setContentTitle("School Assignment Alert")
                .setContentText(message)
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        mNotificationManager.notify(NOTIFICATION_ID++, builder.build());
    }

    public void createNotificationChannel(Context context) {
        mNotificationManager = context.getSystemService(NotificationManager.class);
        NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Due Date Alert!", NotificationManager.IMPORTANCE_HIGH);
        mNotificationManager.createNotificationChannel(notificationChannel);
    }
}
