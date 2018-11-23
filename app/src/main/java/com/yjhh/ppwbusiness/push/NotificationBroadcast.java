package com.yjhh.ppwbusiness.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.umeng.message.UTrack;
import com.umeng.message.entity.UMessage;
import com.yjhh.ppwbusiness.views.merchant.MerchantSettingActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationBroadcast extends BroadcastReceiver {
    public static final String EXTRA_KEY_ACTION = "ACTION";
    public static final String EXTRA_KEY_MSG = "MSG";
    public static final int ACTION_CLICK = 10;
    public static final int ACTION_DISMISS = 11;
    public static final int EXTRA_ACTION_NOT_EXIST = -1;
    private static final String TAG = NotificationBroadcast.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(EXTRA_KEY_MSG);


        Log.i("UmengNotific","自定义通知:"+ message);

        int action = intent.getIntExtra(EXTRA_KEY_ACTION,
                EXTRA_ACTION_NOT_EXIST);
        try {
            UMessage msg = (UMessage) new UMessage(new JSONObject(message));


            UTrack.getInstance(context).setClearPrevMessage(true);
            UTrack.getInstance(context).trackMsgDismissed(msg);

            Intent intentValue  = new Intent(context,MerchantSettingActivity.class);
            intentValue.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentValue);

            switch (action) {
                case ACTION_DISMISS:
                    Log.i(TAG, "dismiss notification");

                    break;
                case ACTION_CLICK:
                    Log.i(TAG, "click notification");
                    UTrack.getInstance(context).setClearPrevMessage(true);
                    MyNotificationService.oldMessage = null;
                   // UTrack.getInstance(context).trackMsgClick(msg);


                    break;
            }
            //
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
