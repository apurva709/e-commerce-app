package com.example.ecommerceapp;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.ecommerceapp.model.Users;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class  message extends FirebaseMessagingService {
    private static Uri sound;
    private final long[] pattern={100,300,300,300};
    private NotificationManager NotificationManager;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        shownotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

    }
    public void shownotification(String title,String message){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"Notification")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.a)
                .setAutoCancel(false)
                .setSound(sound)
                .setVibrate(pattern)
                .setContentText(message);
        sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManagerCompat manager=NotificationManagerCompat.from(this);
        manager.notify(999,builder.build());
    }
}
