package com.yoconciertos;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

public class MyNotificationManager {

    private static final int NOTIFICATION_ID = 0;
    private Context mContext;
    private String NOTIFICATION_CHANNEL_ID = "10";

    public MyNotificationManager(Context context){
        mContext = context;
    }

    public void showNotification(String from, String message, Intent intent){

        PendingIntent pendingIntent = PendingIntent.getActivity(
                mContext.getApplicationContext(),
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.drawable.ic_freesample)
                .setAutoCancel(true)
                .setColor(Color.DKGRAY)
                .setContentIntent(pendingIntent)
                .setContentTitle(from)
                .setContentText(message)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_freesample));


        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.DKGRAY);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
