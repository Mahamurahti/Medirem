package com.example.medirem_project;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationManager extends NotificationManager {
    public static final String CHANNEL_ID_1 = "Channel1";
/**
 * App is the class
 * @author Joonatan Pakkanen
 * @version 1.1 3/2020
 */
public class App extends Application {

    public static final String CHANNEL_1_ID = "channel1";


    @Override
    public void onCreate(){
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_ID_1,
                    "channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("this is channel1");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }
}
