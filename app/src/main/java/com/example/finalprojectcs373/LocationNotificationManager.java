package com.example.finalprojectcs373;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;

public class LocationNotificationManager {

    private static final String CHANNEL_ID = "location_notification_channel";
    private static final int NOTIFICATION_ID = 1;
    private Notification notification;

    private final Context context;
    private final NotificationManager notificationManager;

    public LocationNotificationManager(Context context) {
        this.context = context;
        this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Location", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void sendNotification(Location location) {
        // Create an intent that will be triggered when the notification is clicked
        Intent intent = new Intent(context, StartingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Build the notification
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(context, CHANNEL_ID)
                    .setContentTitle("You are near your destination")
                    .setContentText("Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude())
                    .setSmallIcon(R.drawable.baseline_circle_notifications_24)
                    .setContentIntent(pendingIntent)
                    .build();
        }

        // Send the notification
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}