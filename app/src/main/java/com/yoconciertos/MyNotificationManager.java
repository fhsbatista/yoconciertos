package com.yoconciertos;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

public class MyNotificationManager {

    private Context mContext;
    private int NOTIFICATION_ID = 10;

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

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        Notification notification = builder
                .setSmallIcon(R.mipmap.ic_small_icon)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(from)
                .setContentText(message)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher))
                .build();

        notification.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
