package com.yoconciertos;

import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(Keys.KEY_LINK_NOTIFICATION, remoteMessage.getData().get(Keys.KEY_LINK_NOTIFICATION));

        notifyUser(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), intent);
    }
    public void notifyUser(String from, String message, Intent intent){

        MyNotificationManager myNotificationManager = new MyNotificationManager(getApplicationContext());
        myNotificationManager.showNotification(from, message, intent);
    }
}
