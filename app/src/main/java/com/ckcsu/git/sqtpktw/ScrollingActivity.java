package com.ckcsu.git.sqtpktw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

import Place.Place;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Place place;
        place = (Place) bundle.getSerializable("PLACE");
        /*ArrayList<TextView> TV = new ArrayList<>();
        TextView tag11;
        tag11 = (TextView) findViewById(R.id.tag1);
        TV.add(tag11);*/
        //突然想尝试手写，说的除了手写循环我还能有别的简单办法一样
        ArrayList<String> tags = place.GetTags();
        TextView tag10 = (TextView) findViewById(R.id.tag1);
        tag10.setText(tags.get(0));
        TextView tag11 = (TextView) findViewById(R.id.tag2);
        tag11.setText(tags.get(1));
        TextView tag12 = (TextView) findViewById(R.id.tag3);
        tag12.setText(tags.get(2));
        TextView tag13 = (TextView) findViewById(R.id.tag4);
        tag13.setText(tags.get(3));
        TextView tag14 = (TextView) findViewById(R.id.tag5);
        tag14.setText(tags.get(4));
        Toolbar Tb = (Toolbar)findViewById(R.id.toolbar);






    }
}
