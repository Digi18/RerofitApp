package com.app.retrofitapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MessagingReceive extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

   /*     String str = remoteMessage.getNotification().getTitle();
        Log.d("Title",str);
        String str1 = remoteMessage.getData().get("title");
        Log.d("Payload",str1);  */

        if(remoteMessage.getData().size() > 0){

          //  Map<String, String> data = remoteMessage.getData();

          //  handleData(data);
             Log.d("Data", String.valueOf(remoteMessage.getData()));
          //  Toast.makeText(getApplicationContext(),"data",Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_SHORT).show();
        }
    /*    Intent intent = new Intent(MessagingReceive.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle("FCM NOTIF.");
        notificationBuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());  */

    }

    private void handleData(Map<String,String> data){

           String title = data.get("account");
           String body = data.get("balance");

        Intent intent = new Intent(MessagingReceive.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(body);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());
    }
}
