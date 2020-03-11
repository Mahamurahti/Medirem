package com.example.medirem_project;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

/**
 * NotificationHelper is used to create and manage notification channels. It also constructs notifications.
 * NotificationHelper has ContextWrapper as its superclass.
 * @author Joonatan Pakkanen
 * @version 1.1 3/2020
 */
public class NotificationHelper extends ContextWrapper {
    public static final String channel1ID = "channel1ID";
    public static final String channel1Name = "Channel 1";

    private NotificationManager mManager;

    /**
     *
     * @param base
     */
    public NotificationHelper(Context base){
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannels();
        }
        createChannels();
    }

    /**
     * Creates notification channel and sets default settings for it.
     */
    public void createChannels(){
        NotificationChannel channel1 = new NotificationChannel(channel1ID, channel1Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.colorPrimary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1);
    }

    /**
     * Returns mManager if you call getManager method.
     * @return Returns mManager.
     */
    public NotificationManager getManager(){
        if(mManager == null){
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    /**
     * Sets default settings for notification and creates intent for opening app through notification.
     * @param title Title of the notifications, Declared in AlertReceiver.
     * @param message Message of the notification, Declared in AlertReceiver.
     * @return Returns ChannelID for notification, title of notification, message of notification and
     * icon for notification to use. Also sets autocancel to true so notification gets destroyed after you click it,
     * and sets pending intent for opening application after clicking.
     */
    public NotificationCompat.Builder getChannelNotification(String title, String message){

        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(), channel1ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.chillpilllogoround)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
    }
}
