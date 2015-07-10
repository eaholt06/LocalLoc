package com.cse4471.ohiostate.localloc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
        public void run(){
                Intent openMainActivity = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(openMainActivity);
                finish();
            }
        }, 2500);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

}