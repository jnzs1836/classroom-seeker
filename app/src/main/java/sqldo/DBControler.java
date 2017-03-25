package sqldo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Place.Place;

import static sqldo.FeedReaderContract.FeedEntry.COLUMN_NAME_LOCATION_FLOOR;
import static sqldo.FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE;

/**
 * Created by Dick on 2017/3/16.
 */

public class DBControler {
    private int  id;

    public static final int[] LocationConverter = {10000,1000,1};
    private SQLiteDatabase db;



    public DBControler(SQLiteDatabase db){
        this.db = db;
    }

    public void InsertP(Place place){
        ContentValues values = new ContentValues();

        String Name = place.GetName();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,Name);

        int[] Location = place.GetLocation();

        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, Location[0]);
        values.put(COLUMN_NAME_LOCATION_FLOOR, Location[1]);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NUMBER, Location[2]);

        ArrayList<String> Tags = place.GetTags();
        int i;
        for(i= 0;i<10;i++) {
            if(i < Tags.size()) {
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE_TAGS[i], Tags.get(i));
                if(this.QueryTag(Tags.get(i))){
                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,Tags.get(i));
                    long newRowIdTags = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME_TAGS, null, values);
                }
            }
        }
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);


        //在TAG数据库中加入tag（先检验是否已存在）


    }

    public Place GetP(String name) throws Exception{
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_LOCATION_FLOOR,
                        //刚刚这里漏打了两个标题，害我debug了两个小时，。这种事我会说么
                FeedReaderContract.FeedEntry.COLUMN_NAME_NUMBER,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE_TAGS[0],
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE_TAGS[1],
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE_TAGS[2],
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE_TAGS[3],
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE_TAGS[4]
        };

        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { name};


        String sortOrder =
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor c = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        Place place = new Place();

        if(!c.moveToNext()){
            throw new Exception("NO RESULT");
        }
        c.moveToFirst();
        ArrayList<String> Tags = new ArrayList<String>();
        for(int i =0; i<5;i++){
            int  a = c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE_TAGS[i]);
            String tag = c.getString(a);
            Tags.add(tag);
            name =tag;
           }

        int  Location[] = new int[3];

        int a = c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE);
        Location[0] = c.getInt(a);
        //md 为什么这里能过？？？？？
        //我知道了hhh
        //和自己聊天真有趣

        int b = c.getColumnIndex(COLUMN_NAME_LOCATION_FLOOR);
        try{
        Location[1] =c.getInt(b);

        int d = c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_NUMBER);
        Location[2] = c.getInt(d);
         //md bug在这
            //md 为什么LOCATION值没变;
        place.setAll(name,Tags,Location);
        return place;}
        catch(Exception e) {Place aplace= new Place();
            ArrayList<String> teste = new ArrayList<String>();
            teste.add("hell");
            int[] testl = new int[3];
            testl[0] = 0;
            testl[1] = 1;
            testl[2] = 2;
            aplace.setAll("SECOND STEP",teste,testl);
        return aplace;}
    }

    void DelP(String name){
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = { name };
// Issue SQL statement.
        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
    }

    public ArrayList<DictionaryForPlacesql> SearchPlaceByTag(String tag){
        ArrayList<DictionaryForPlacesql> output = new ArrayList<DictionaryForPlacesql>();

        for(int i=0;i<5;i++){
            String[] projection = {
                    FeedReaderContract.FeedEntry._ID,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE_TAGS[i]
            };
            String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
            String[] selectionArgs = { tag};
            String sortOrder =
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " DESC";

            Cursor c = db.query(
                    FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                    projection,                               // The columns to return
                    selection,                                // The columns for the WHERE clause
                    selectionArgs,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    sortOrder                                 // The sort order
            );
            Place place = new Place();

            int id = c.getColumnIndex(FeedReaderContract.FeedEntry._ID);
            int name = c.getColumnIndex(COLUMN_NAME_TITLE);
            DictionaryForPlacesql DPS;

            while(c.moveToNext()){
                long idr = c.getLong(id);
                if(idr==-1){break;}
                String namer = c.getString(name);
                DPS = new DictionaryForPlacesql(idr,namer);
                output.add(DPS);
            }
        }




        return output;
    }

    public boolean QueryTag(String tag){

        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE};
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { tag};
        String sortOrder =
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor c = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        if(c.moveToNext()){
            return true;
        }

        else return false;


    }

    public String QueryTagById(int id){
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE};
        String selection = FeedReaderContract.FeedEntry._ID + " = ?";
        String[] selectionArgs = { Integer.toString(id)};
        String sortOrder =
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor c = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder  );
        c.moveToFirst();
        int idex = c.getColumnIndex(COLUMN_NAME_TITLE);
        String name = c.getString(idex);
        return name;




}}
