package com.example.medirem_project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent){
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("Title", "Message");
        notificationHelper.getManager().notify(1, nb.build());
    }
}
