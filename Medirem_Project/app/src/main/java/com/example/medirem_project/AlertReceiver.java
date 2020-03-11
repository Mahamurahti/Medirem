package com.example.medirem_project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

/**
 * Alert Receiver class receives the alert information and passes it for the notification helper
 * class. In this class the information what the notifications holds is defined.
 * @author Joonatan Pakkanen
 * @version 1.1 3/2020
 */
public class AlertReceiver extends BroadcastReceiver {

    /**
     * This method receives the information about the alarm and acts accordingly.
     * @param context the interface you are in (context)
     * @param intent the message you want to deliver (intent)
     */
    public void onReceive(Context context, Intent intent){
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("Medicine Reminder", "Hey! Remember to take your medicine!");
        notificationHelper.getManager().notify(1, nb.build());
    }
}
