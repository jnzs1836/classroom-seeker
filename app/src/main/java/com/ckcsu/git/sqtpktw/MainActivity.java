package com.ckcsu.git.sqtpktw;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import Place.Place;

import sqldo.DBControler;
import sqldo.FeedReaderDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Place palace = new Place();
        String[] array = {"rich", "good", "wonderful", "relaxing", "big"};
        ArrayList<String> tags = new ArrayList<String>(Arrays.asList(array));
        int[] location = {3, 2, 203};
        palace.setAll("TEST_WELL", tags, location);
        DBControler DBC = new DBControler(db);
        DBC.InsertP(palace);
    }

    public void doit(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void place_intent(View view){
        Intent intent = new Intent(this, PlaceActivity.class);
        startActivity(intent);
    }

    public void single_place_intent(View view){
        Intent intent = new Intent(this, OnePlaceActivity.class);
        startActivity(intent);
        Bundle bundle = new Bundle();
        bundle.putString("hello world","boo");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void gotoSearch(View view){
       Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }

}
