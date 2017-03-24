package com.ckcsu.git.sqtpktw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class OnePlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_place);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("hello world");
        Log.i("hello",name);


    }
}
