package com.zhg.demo.notifycation;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.id_send_notifycation).setOnClickListener(v->sendNotification());
    }

    private void sendNotification() {
        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        Notification notification=builder.setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_content)).setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_LIGHTS).setSmallIcon(R.mipmap.icon_weixin)
                .setColor(Color.parseColor("#880000FF"))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.weixin_raw))
                .setContentIntent(PendingIntent.getActivities(this,0x0001,new Intent[]{new Intent(this,MainActivity.class)},PendingIntent.FLAG_UPDATE_CURRENT))
                .build();

        notificationManager.notify(1,notification);
    }
}
