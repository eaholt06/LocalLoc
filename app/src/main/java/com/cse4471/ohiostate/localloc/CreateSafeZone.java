package com.cse4471.ohiostate.localloc;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class CreateSafeZone extends FragmentActivity // implements CSZBluetoothFragment.OnCompleteListener1
 {
    private SafeZone sz;
    FragmentManager fm;
    int currentFrag;
    private static final String TAG = CreateSafeZone.class.getSimpleName();
    CSZNameFragment nameFrag;
    boolean devidSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_safe_zone);
    /*    if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.createSafeZone_fragContainer, new PlaceholderFragment())
                    .commit();
        } */

        nameFrag = new CSZNameFragment();
        fm = getFragmentManager();
        //Fragment fragment = fm.findFragmentById(R.id.createSafeZone_fragContainer);
        android.app.FragmentTransaction fragmentTransaction= fm.beginTransaction();
        fragmentTransaction.add(R.id.createSafeZone_fragContainer, nameFrag, "nameFrag").commit();
        currentFrag = 1;
        sz = new SafeZone();
        devidSet = sz.setDeviceId(this);
    }

    public void btdevidTextClick(){
        EditText et = (EditText) fm.findFragmentByTag("btFrag").getView().findViewById(R.id.bt_devidEnter);
        et.setText(sz.getDeviceId());
    }

    public void btmacTextClick(){
        EditText et2 = (EditText) fm.findFragmentByTag("btFrag").getView().findViewById(R.id.bt_macEnter);
        et2.setText(sz.getMacId());
    }

    public void wifiTextClick(){
        EditText wifiet = (EditText) fm.findFragmentByTag("wifiFrag").getView().findViewById(R.id.wifi_devidEnter);
        wifiet.setText(sz.getDeviceId());
    }

    public void submitSafeZone(View v){
        Data data = new Data();
        int buttonId = v.getId();
        if(buttonId == R.id.CSZ_btSubmit){
            EditText et1 = (EditText)fm.findFragmentByTag("btFrag").getView().findViewById(R.id.bt_devidEnter);
            EditText et2 = (EditText)fm.findFragmentByTag("btFrag").getView().findViewById(R.id.bt_macEnter);
            sz.setDeviceId(et1.getText().toString(), et2.getText().toString());

            //Add safe zone to database
            data.AddToBluetooth(sz.getTitle(), sz.getDeviceId(), sz.getMacId());
        } else if(buttonId == R.id.CSZ_wifiSubmit){
            EditText et = (EditText)fm.findFragmentByTag("wifiFrag").getView().findViewById(R.id.wifi_devidEnter);
            //Add wifi safe zone to database
            data.AddToWiFi(sz.getTitle(), sz.getDeviceId());
        }
        Toast.makeText(getApplicationContext(), "Safe Zone saved successfully", Toast.LENGTH_LONG).show();
    }

    public void selectFragment(View v){
        int buttonID = v.getId();
        Button btn = (Button)findViewById(buttonID);

        if(buttonID == R.id.button_next) {
            switch (currentFrag) {
                case 1:
                    //Pressing next button saves user defined title in SafeZone object
                    EditText et = (EditText) fm.findFragmentByTag("nameFrag").getView().findViewById(R.id.name_input);
                    if (et == null) {
                        Log.d(TAG, "edittext is empty");
                    } else {
                        String title = et.getText().toString();
                        if (title.isEmpty()) {
                            Log.d(TAG, "edittext is fine, gettext is empty");
                            sz.setTitle("Default");
                        } else {
                            Log.d(TAG, "All is well. String is: " + title);

                            sz.setTitle(title);
                        }
                    }
                    android.app.FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.createSafeZone_fragContainer, new CSZTypeFragment()).commit();

                    currentFrag = 2;
                    break;
                case 2:
                    //Pressing next button saves user's type choice in SafeZone object
                    RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
                    RadioButton rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());

                    if (rb == null) {
                        Toast.makeText(getApplicationContext(), "Please pick a Safe Zone type",
                                Toast.LENGTH_LONG).show();
                    } else {
                        String zt = rb.getText().toString();
                        sz.setZoneType(zt);

                        String[] types = getApplicationContext().getResources().getStringArray(R.array.zone_types);
                /*    if (zt.equals(types[0])) {
                        android.app.FragmentTransaction ft2 = fm.beginTransaction();
                        ft2.replace(R.id.createSafeZone_fragContainer, new CSZGeoFragment()).commit();
                }*/
                        if (zt.equals(types[1])) {
                            android.app.FragmentTransaction ft2 = fm.beginTransaction();
                            ft2.replace(R.id.createSafeZone_fragContainer, new CSZBluetoothFragment(), "btFrag").commit();
                            fm.executePendingTransactions();
                            Log.d(TAG, "Inside SelectFragment-case2, bluetooth transaction added ");
                        } else if (zt.equals(types[2])) {
                            android.app.FragmentTransaction ft2 = fm.beginTransaction();
                            ft2.replace(R.id.createSafeZone_fragContainer, new CSZWifiFragment(), "wifiFrag").commit();
                            fm.executePendingTransactions();
                            Log.d(TAG, "Inside SelectFragment-case2, wifi transaction added ");
                        }
                        currentFrag = 3;
                        Button next = (Button) findViewById(R.id.button_next);
                        next.setVisibility(View.INVISIBLE);
                    }
                }
            }
        if(buttonID == R.id.button_back){
            switch (currentFrag){
                case 1:
                    //Goes back to main activity and 'deletes' SafeZone object
                    sz = null;
                    this.finish();
                case 2:
                    android.app.FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.createSafeZone_fragContainer, new CSZNameFragment()).commit();
                    currentFrag = 1;
                    break;
                case 3:
                    android.app.FragmentTransaction ft2 = fm.beginTransaction();
                    ft2.replace(R.id.createSafeZone_fragContainer, new CSZTypeFragment()).commit();
                    currentFrag = 2;
                    Button next = (Button)findViewById(R.id.button_next);
                    next.setVisibility(View.VISIBLE);
                    break;
            }
        }
        btn.setPressed(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_safe_zone, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
