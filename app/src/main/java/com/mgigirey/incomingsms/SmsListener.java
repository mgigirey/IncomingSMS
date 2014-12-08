package com.mgigirey.incomingsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by mgigirey on 08/12/2014.
 */
public class SmsListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (Object aPdusObj : pdusObj) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj);

                    String senderNum = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);

                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, "senderNum: " + senderNum + ", message: " + message, duration);
                    toast.show();

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }
}
