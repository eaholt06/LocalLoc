package com.cse4471.ohiostate.localloc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class LockScreenMainActivity extends AppCompatActivity{

    private String masterP;
    private String buttonPress;
    private int pinLength = 4;
    private TextView mainText = (TextView)findViewById(R.id.Pin);

    @Override
    protected void onCreate(Bundle savedInstanceState){


        boolean safe = ZoneChecker.updateZone(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainText  = (TextView)findViewById(R.id.Pin);
        openLockScreen();
        startService(new Intent(this, LockScreenService.class));
        final Button button1 = (Button)findViewById(R.id.button1);
        final Button button2 = (Button)findViewById(R.id.button2);
        final Button button3 = (Button)findViewById(R.id.button3);
        final Button button4 = (Button)findViewById(R.id.button4);
        final Button button5 = (Button)findViewById(R.id.button5);
        final Button button6 = (Button)findViewById(R.id.button6);
        final Button button7 = (Button)findViewById(R.id.button7);
        final Button button8 = (Button)findViewById(R.id.button8);
        final Button button9 = (Button)findViewById(R.id.button9);
        final Button button0 = (Button)findViewById(R.id.button0);
        final Button confirmButton = (Button)findViewById(R.id.confirm);
        checkPin();
        if(buttonPress.length() < pinLength){
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPress = buttonPress.concat(button1.getText().toString());
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPress = buttonPress.concat(button2.getText().toString());
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPress = buttonPress.concat(button3.getText().toString());
                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPress = buttonPress.concat(button4.getText().toString());
                }
            });
            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPress = buttonPress.concat(button5.getText().toString());
                }
            });
            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPress = buttonPress.concat(button6.getText().toString());
                }
            });
            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPress = buttonPress.concat(button7.getText().toString());
                }
            });
            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPress = buttonPress.concat(button8.getText().toString());
                }
            });button9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPress = buttonPress.concat(button9.getText().toString());
                }
            });button0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPress = buttonPress.concat(button0.getText().toString());
                }
            });
        }

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonPress.length() == pinLength && buttonPress.equals(masterP)){
                    Intent startMain = new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startMain);
                    mainText.setText("Enter PIN");
                }
                else {
                    buttonPress = "";
                    mainText.setText("Incorrect, Renter Pin");
                }
            }
        });
    }

    public void openLockScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    public void setPin(){
            mainText.setText("Enter Master PIN");
            if(buttonPress.length() == 4){
                masterP = buttonPress;
            }
    }

    public void unlockScreen(View view) {
        android.os.Process.killProcess((android.os.Process.myPid()));
    }

    private void checkPin(){
        if(masterP.equals("")){
            setPin();
        }
    }

    @Override
    public void onBackPressed() {

    }
}
