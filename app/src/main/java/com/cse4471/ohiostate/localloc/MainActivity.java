package com.cse4471.ohiostate.localloc;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

//Previously extended appCompatActivity
public class MainActivity extends Activity {

//    private DrawerLayout drawerLayout;
//    private ListView drawerList;

    public void buttonOnClick(View v){
        Intent nextActivity;
        v.setBackground(getResources().getDrawable(R.drawable.buttonHover));
        switch (v.getId()) {
            case R.id.Button1:
                nextActivity = new Intent(MainActivity.this, createSafeZone.class);
                break;
            case R.id.Button2:
                nextActivity = new Intent(MainActivity.this, modifySafeZone.class);
                break;
            case R.id.Button3:
                nextActivity = new Intent(MainActivity.this, deleteSafeZone.class);
                break;
            case R.id.Button4:
                nextActivity = new Intent(MainActivity.this, Settings.class);
                break;
            default:
                nextActivity = new Intent();
        }
        startActivity(nextActivity);
    }

    View.OnHoverListener buttonHovering = new View.OnHoverListener(){

        @Override
        public boolean onHover(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_HOVER_ENTER:
                    v.setBackgroundResource(R.drawable.buttonHover);
                    break;
                case MotionEvent.ACTION_HOVER_MOVE:
                 //   mMessageTextView.setText("Move: " + event.getX() + ":" + event.getY());
                    break;
                case MotionEvent.ACTION_HOVER_EXIT:
                    v.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
                    break;
            }
            return false;
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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