package com.ckcsu.git.sqtpktw;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;

import Place.Place;
import event.QueryEvent;
import event.TestEvent;
import sqldo.DBControler;
import sqldo.FeedReaderDbHelper;

public class PlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EventBus.getDefault().register(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        EventBus.getDefault().post(new TestEvent());


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new TestEvent());
                FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                Place palace = new Place();
                String[] array = {"rich", "good", "wonderful", "relaxing", "big"};
                ArrayList<String> tags = new ArrayList<String>(Arrays.asList(array));
                int[] location = {3, 2, 203};
                palace.setAll("TEST_WELL", tags, location);
                DBControler DBC = new DBControler(db);
                DBC.InsertP(palace);

                db = mDbHelper.getReadableDatabase();
                DBC = new DBControler(db);
                Place placeget = new Place();
                placeget.setAll("nothing",tags,location);

                try {
                    placeget = DBC.GetP("TEST_WELL");
                }catch(Exception e)  {Log.v("hello","hi");}
                //开心，这里的bug终于好了，原来只是因为一个大小写
                //Log.v("he","he");

                EventBus.getDefault().post(new QueryEvent(placeget));


            }
        });

        thread.start();


    }
@Subscribe
    public void TestE(TestEvent TE){
        TextView namel = (TextView)findViewById(R.id.Location);
        namel.setText("hello");
    }


    public void refresh(View view){
        TextView namel = (TextView)findViewById(R.id.Location);
        namel.setText("hello");
        namel.setVisibility(View.INVISIBLE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(QueryEvent event){
        try{String name = event.getName();
        TextView namel = (TextView)findViewById(R.id.Name1);
            String ab = event.FunctionForTest();
        namel.setText(name+ab+"!!!!!!!!!!");}
        catch ( Exception e) {
            TextView namel = (TextView)findViewById(R.id.Location);
            namel.setText("hello");
            namel.setVisibility(View.INVISIBLE);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册



    }




}


