package com.cse4471.ohiostate.localloc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;


public class LockScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        openLockScreen();
        startService(new Intent(this, LockScreenService.class));

        setContentView(R.layout.lockscreenactivity_main);

    }

    public void openLockScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    @Override
    public void onBackPressed() {

    }

    public void unlockScreen(View view) {
        android.os.Process.killProcess((android.os.Process.myPid()));
    }
}