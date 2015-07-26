package com.cse4471.ohiostate.localloc;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class LockScreenService extends Service {

    private BroadcastReceiver lockReceiver;

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override@SuppressWarnings("deprecation")
    public void onCreate(){
        KeyguardManager.KeyguardLock key;
        KeyguardManager keym = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);

        key = keym.newKeyguardLock("IN");

        key.disableKeyguard();

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);

        lockReceiver = new LockScreenReceiver();
        registerReceiver(lockReceiver, filter);

        super.onCreate();
    }

    @Override
    public void onDestroy(){
        unregisterReceiver(lockReceiver);
        super.onDestroy();
    }

}
