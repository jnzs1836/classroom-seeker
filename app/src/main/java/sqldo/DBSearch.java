package sqldo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Place.Place;
import Place.PlaceforSearch;

/**
 * Created by Dick on 2017/3/16.
 */

public class DBSearch {

    private ArrayList<Place> SearchByTag(SQLiteDatabase db,String Tag){
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
        };


        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE_TAGS[0] + " = ?" + " OR" +
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE_TAGS[1] + "= ?"+" OR"+
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE_TAGS[2] + "= ?"+" OR"+
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE_TAGS[3] + "= ?"+" OR"+
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE_TAGS[4] + "= ?"+" OR";
        String[] selectionArgs = { Tag,Tag,Tag,Tag,Tag };


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
        c.moveToFirst();
        ArrayList<Place> placelist =new ArrayList<Place>();
        while(c.moveToNext()==true){
            Place place = new Place();
            int nameco = c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE);
            String name = c.getString(nameco);
            ArrayList<String> Tags = new ArrayList<String>();
            for(int i =0; i<5;i++){
                int  a = c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE_TAGS[i]);
                String tag = c.getString(a);
                Tags.add(tag);}
            int  Location[] = new int[3];
            int a = c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE);
            Location[0] = c.getInt(a);
            int b = c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_LOCATION_FLOOR);
            Location[1] = c.getInt(b);
            int d = c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_NUMBER);
            Location[2] = c.getInt(d);
            place.setAll(name,Tags,Location);
            placelist.add(place);
        }
        return placelist;
    }

    public DBSearch(PlaceforSearch PS){


    }
}
