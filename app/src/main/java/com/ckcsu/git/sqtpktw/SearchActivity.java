package com.ckcsu.git.sqtpktw;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import Place.Place;
import Tags.TagsGenerator;
import event.QueryEventN;
import event.SearchErrorEvent;
import sqldo.DBControler;
import sqldo.FeedReaderDbHelper;




public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        TagsGenerator TG = new TagsGenerator();
        ArrayList<String> get = TG.GenerateTags(getApplicationContext());

        TextView TX0 = (TextView) findViewById(R.id.button3);
        TextView TX1 = (TextView) findViewById(R.id.button4);
        TextView TX2 = (TextView) findViewById(R.id.button5);
        TextView TX3 = (TextView) findViewById(R.id.button6);
        TextView TX4 = (TextView) findViewById(R.id.button7);
        ArrayList<TextView> button = new ArrayList<>();
        button.add(TX0);
        button.add(TX1);
        button.add(TX2);
        button.add(TX3);
        button.add(TX4);
        for(int i = 0;i <5;i++){
            String tem = get.get(i);
            button.get(i).setText(tem);
        }





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

    public void gotoPlaceByTag(View view){
        Intent intent = new Intent(this,ScrollingActivity.class);
        TextView TV = (TextView) view;
        CharSequence Tag = TV.getText();
        String STag = Tag.toString();
        Bundle bundle = new Bundle();
        bundle.putString("name",STag);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
