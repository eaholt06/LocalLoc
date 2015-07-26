package com.cse4471.ohiostate.localloc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LockScreenMainActivity extends AppCompatActivity{

    private String masterP;
    private String buttonPress;
    private int pinLength = 4;
    private TextView mainText = (TextView)findViewById(R.id.Pin_text);
    private Button button1 = (Button)findViewById(R.id.button1);
    private Button button2 = (Button)findViewById(R.id.button2);
    private Button button3 = (Button)findViewById(R.id.button3);
    private Button button4 = (Button)findViewById(R.id.button4);
    private Button button5 = (Button)findViewById(R.id.button5);
    private Button button6 = (Button)findViewById(R.id.button6);
    private Button button7 = (Button)findViewById(R.id.button7);
    private Button button8 = (Button)findViewById(R.id.button8);
    private Button button9 = (Button)findViewById(R.id.button9);
    private Button button0 = (Button)findViewById(R.id.button0);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lockscreenactivity_main);
        if(masterP.equals("")){
            setPin(masterP);
        }

    }


    @Override
    public void onClick(View v){

        if(buttonPress.length() < pinLength){
            switch (v.getId()) {
                case R.id.button1:
                    buttonPress = buttonPress.concat(button1.getText().toString());
                    break;
                case R.id.button2:
                    buttonPress = buttonPress.concat(button2.getText().toString());
                    break;
                case R.id.button3:
                    buttonPress = buttonPress.concat(button3.getText().toString());
                    break;
                case R.id.button4:
                    buttonPress = buttonPress.concat(button4.getText().toString());
                    break;
                case R.id.button5:
                    buttonPress = buttonPress.concat(button5.getText().toString());
                    break;
                case R.id.button6:
                    buttonPress = buttonPress.concat(button6.getText().toString());
                    break;
                case R.id.button7:
                    buttonPress = buttonPress.concat(button7.getText().toString());
                    break;
                case R.id.button8:
                    buttonPress = buttonPress.concat(button8.getText().toString());
                    break;
                case R.id.button9:
                    buttonPress = buttonPress.concat(button9.getText().toString());
                    break;
                case R.id.button0:
                    buttonPress = buttonPress.concat(button0.getText().toString());
                    break;
            }
        }
    }

    public void setPin(String masterP){
            mainText.setText("Enter Master PIN");
            if(buttonPress.length() == 4){
                masterP = buttonPress;
            }
    }

    public void onStart(){
        super.onStart();
        //check listeners here first
        checkPin(masterP);
    }

    private void checkPin(String string){
        if(masterP.equals("")){
            setPin(masterP);
        }
    }
}
