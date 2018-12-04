package com.finchild.hoppateam.sda4.finchild.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.content.Context;
import com.finchild.hoppateam.sda4.finchild.AccountChildPurchases;
import com.finchild.hoppateam.sda4.finchild.R;

public class Notification {
    private final static  String CHANNEL_ID="personal Notification";
    public static void createNotificationChannel(Context context,String childName, double amount, String notifType) {
        // Toast.makeText(getApplicationContext(),"Inside create Channel",Toast.LENGTH_SHORT).show();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "char for notification";
            String description = "string for notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager =context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            //  sendNotification();

            Intent resultIntent= new Intent(context,AccountChildPurchases.class);

            PendingIntent resultPendingIntent=PendingIntent.getActivity(context,2,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID);
            String notifTitle="";
            String notifText="";
            int id=000;
            if(notifType.equals("limitNotif")) {
                notifTitle="Child Purchase";
                notifText=childName +" has exceeded the limit of "+amount+ " for today";
                id=001;
            }else if(notifType.equals("autofillNotif")){
                notifTitle="Child Account Autofill";
                notifText=amount+"kr has been filled into account of "+childName +"";
                id=002;
            }
                builder.setSmallIcon(R.drawable.childaccount)
                        .setContentTitle(notifTitle)
                        .setContentText(notifText)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true)
                        .setContentIntent(resultPendingIntent);



                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(id,builder.build());


        }

    }



}
