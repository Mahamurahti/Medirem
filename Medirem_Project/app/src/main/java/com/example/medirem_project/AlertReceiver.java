package com.example.medirem_project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

/**
 * Alertreceiver
 * @author Joonatan Pakkanen
 * @version 1.1 3/2020
 */
public class AlertReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent){
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("Medicine Reminder", "Hey! Remember to take your medicine!");
        notificationHelper.getManager().notify(1, nb.build());
    }
}
