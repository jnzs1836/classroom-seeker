package Place;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import sqldo.DBControler;
import sqldo.FeedReaderDbHelper;

/**
 * Created by Dick on 2017/3/25.
 */

public class TagsGenerator {

    ArrayList<String> AllTags= new ArrayList<>();
    int  Count =5;
    public ArrayList<String> GenerateTags(Context context){

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
        SQLiteDatabase db;
        DBControler DBC;
        db = mDbHelper.getReadableDatabase();
        DBC = new DBControler(db);
        int i = 0;
        ArrayList<String> output = new ArrayList<>();
        try {int index = (int)(Math.random()*Count);


        while(i<5){
            String tagname = DBC.QueryTagById(index);
            output.add(tagname);
            i++;
        }}
        catch (Exception e){
            while(i<5){
            output.add("error");
            i++;}
        }
    return output;}
}
