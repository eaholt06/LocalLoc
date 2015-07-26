package com.cse4471.ohiostate.localloc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

//Previously extended appCompatActivity
public class MainActivity
        extends     AppCompatActivity
        implements  GoogleApiClient.ConnectionCallbacks,
                    GoogleApiClient.OnConnectionFailedListener {

//    private DrawerLayout drawerLayout;
//    private ListView drawerList;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ZoneChecker zone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.zone = new ZoneChecker(this);

    }

    public void buttonOnClick(View v){
        Intent nextActivity;
        //v.setBackground(getResources().getDrawable(R.drawable.button_hover));
        switch (v.getId()) {
            case R.id.Button1:
                nextActivity = new Intent(MainActivity.this, createSafeZone.class);
                Log.d(TAG, "set safe zone button pressed");
                break;
            case R.id.Button2:
                nextActivity = new Intent(MainActivity.this, modifySafeZone.class);
                Log.d(TAG, "modify safe zone button pressed");
                break;
            case R.id.Button3:
                nextActivity = new Intent(MainActivity.this, deleteSafeZone.class);
                Log.d(TAG, "delete safe zone button pressed");
                break;
            case R.id.Button4:
                nextActivity = new Intent(MainActivity.this, Settings.class);
                Log.d(TAG, "settings button pressed");
                break;
            default:
                nextActivity = new Intent();
                Log.d(TAG, "Something went wrong");
        }
        startActivity(nextActivity);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        setContentView(R.layout.activity_main);
        //Reset background for all buttons
        Button b1 = (Button)findViewById(R.id.Button1);
        b1.setPressed(false);
        Button b2 = (Button)findViewById(R.id.Button2);
        b2.setPressed(false);
        Button b3 = (Button)findViewById(R.id.Button3);
        b3.setPressed(false);
        Button b4 = (Button)findViewById(R.id.Button4);
        b4.setPressed(false);
    }


    @Override
    public void onConnected (Bundle bundle) {

    }


    @Override
    public void onConnectionSuspended (int i) {

    }


    @Override
    public void onConnectionFailed (ConnectionResult connectionResult) {

    }
}
/*  //      drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
  //    drawerList = (ListView) findViewById(R.id.drawer_list);

        drawerList.setAdapter(new ArrayAdapter<>(
                this,
                R.layout.drawer_list_item,
                getResources().getStringArray(R.array.menu_items)));

//        drawerList.setOnItemClickListener(new DrawerItemClickListener());
//        Button Button1 = (Button)findViewById(R.id.Button1);
//        Button Button2 = (Button)findViewById(R.id.Button2);
//        Button Button3 = (Button)findViewById(R.id.Button3);
//        Button Button4 = (Button)findViewById(R.id.Button4);

//        final TextView mainText = (TextView)findViewById(R.id.main_text);
//        Button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                mainText.setText("Hello~~");
//            }
//        });
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            if(position == 0){
                findViewById(R.id.main_content).setBackgroundColor(getResources().getColor(
                        android.R.color.holo_red_dark));
            }
            else{
                findViewById(R.id.main_content).setBackgroundColor(getResources().getColor(
                        android.R.color.holo_blue_dark));
            }
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_intro, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
*/