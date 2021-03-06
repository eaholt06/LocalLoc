package com.cse4471.ohiostate.localloc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class LockScreenReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();

        if(action.equals(Intent.ACTION_SCREEN_OFF) ||
                action.equals(Intent.ACTION_BOOT_COMPLETED)){
            Intent nextActivity = new Intent(context, LockScreenMainActivity.class);
            nextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(nextActivity);
        }
    }
}
