package com.ckcsu.git.sqtpktw;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import Place.Place;
import event.QueryEventN;
import event.SearchErrorEvent;
import sqldo.DBControler;
import sqldo.FeedReaderDbHelper;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void gotoPlace(View view){
        EventBus.getDefault().register(this);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                EditText editT = (EditText)findViewById(R.id.editText);
                String str = editT.getText().toString();
                //EventBus.getDefault().post(new TestEvent());test完了，有点不舍得删了你，留着注释吧
                FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
                SQLiteDatabase db;
                DBControler DBC;
                //知道为什么这里要分开写么嘻嘻
                db = mDbHelper.getReadableDatabase();
                DBC = new DBControler(db);
                Place placeget = new Place();
                int[] location = {-1,-1,-1};
                ArrayList<String> tags = new ArrayList<>();
                placeget.setAll("nothing",tags,location);

                try {
                    placeget = DBC.GetP(str);
                    EventBus.getDefault().post(new QueryEventN(placeget));
                }catch(Exception e)  {
                    SearchErrorEvent SEE = new SearchErrorEvent();
                    EventBus.getDefault().post(SEE);
                    }
                //开心，这里的bug终于好了，原来只是因为一个大小写
                //Log.v("he","he");




            }
        });
        thread.run();


    }
    @Subscribe
    public void gotoPlacec(QueryEventN QE){
        Bundle bundle = new Bundle();
        Place place = QE.getPlace();
        bundle.putSerializable("PLACE",place);
        Intent intent = new Intent(this, ScrollingActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Subscribe
    public void SearchError(SearchErrorEvent SEE){
        EditText edite = (EditText)findViewById(R.id.editText);
        edite.setText("Please input again.");
    }
}
