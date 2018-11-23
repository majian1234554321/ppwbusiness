package com.yjhh.ppwbusiness.push;


import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import com.umeng.message.UTrack;
import com.umeng.message.entity.UMessage;

import com.yjhh.ppwbusiness.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MyNotificationService extends Service {

    public static UMessage oldMessage = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return super.onStartCommand(null, flags, startId);
        }
        String message = intent.getStringExtra("UmengMsg");

        Log.i("UmengNotific", message);

        try {
            UMessage msg = new UMessage(new JSONObject(message));
            if (oldMessage != null) {
                UTrack.getInstance(getApplicationContext()).setClearPrevMessage(true);
                UTrack.getInstance(getApplicationContext()).trackMsgDismissed(oldMessage);
            }
            showNotification(msg);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void showNotification(UMessage msg) {


        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_HIGH);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
            Notification.Builder mBuilder = new Notification.Builder(this, "channel_id");
            mBuilder.setContentTitle(msg.title)
                    .setContentText(msg.text)
                    .setTicker(msg.ticker)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.umeng_push_notification_default_small_icon)
                    .setAutoCancel(true);
            Notification notification =  mBuilder.build();

            PendingIntent clickPendingIntent = getClickPendingIntent(this, msg);
            notification.deleteIntent = getDismissPendingIntent(this, msg);
            notification.contentIntent = clickPendingIntent;
            assert manager != null;
            manager.notify(100, notification);


        } else {
            oldMessage = msg;
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancelAll();


            Notification.Builder mBuilder = new Notification.Builder(this);
            mBuilder.setContentTitle(msg.title)
                    .setContentText(msg.text)
                    .setTicker(msg.ticker)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.umeng_push_notification_default_small_icon)
                    .setAutoCancel(true);
            Notification notification =  mBuilder.build();


            PendingIntent clickPendingIntent = getClickPendingIntent(this, msg);
            notification.deleteIntent = getDismissPendingIntent(this, msg);
            notification.contentIntent = clickPendingIntent;
            manager.notify(100, notification);
        }



    }

    public PendingIntent getClickPendingIntent(Context context, UMessage msg) {
        Intent clickIntent = new Intent();
        clickIntent.setClass(context, NotificationBroadcast.class);
        clickIntent.putExtra(NotificationBroadcast.EXTRA_KEY_MSG,
                msg.getRaw().toString());
        clickIntent.putExtra(NotificationBroadcast.EXTRA_KEY_ACTION,
                NotificationBroadcast.ACTION_CLICK);
        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(context,
                (int) (System.currentTimeMillis()),
                clickIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        return clickPendingIntent;
    }

    public PendingIntent getDismissPendingIntent(Context context, UMessage msg) {
        Intent deleteIntent = new Intent();
        deleteIntent.setClass(context, NotificationBroadcast.class);
        deleteIntent.putExtra(NotificationBroadcast.EXTRA_KEY_MSG,
                msg.getRaw().toString());
        deleteIntent.putExtra(
                NotificationBroadcast.EXTRA_KEY_ACTION,
                NotificationBroadcast.ACTION_DISMISS);
        PendingIntent deletePendingIntent = PendingIntent.getBroadcast(context,
                (int) (System.currentTimeMillis() + 1),
                deleteIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        return deletePendingIntent;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}