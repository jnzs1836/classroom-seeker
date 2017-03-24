package sqldo;

import android.provider.BaseColumns;

/**
 * Created by Dick on 2017/3/9.
 */

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
        public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "Name";
        public static final String COLUMN_NAME_SUBTITLE = "Location";
        public static final String COLUMN_NAME_LOCATION_FLOOR = "Floor";
        public static final String COLUMN_NAME_NUMBER = "Number";
        public static final String[] COLUMN_NAME_TITLE_TAGS = {"Tags1","Tags2","Tags3","Tags4","Tags5"};
        public static final String TEXT_TYPE = " TEXT";
        public static final String INTEGER_TYPE = " INT";
        public static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COLUMN_NAME_SUBTITLE + INTEGER_TYPE + COMMA_SEP+
                        COLUMN_NAME_LOCATION_FLOOR + INTEGER_TYPE + COMMA_SEP+
                        COLUMN_NAME_NUMBER + INTEGER_TYPE + COMMA_SEP+
                        COLUMN_NAME_TITLE_TAGS[0] + TEXT_TYPE+COMMA_SEP+
                        COLUMN_NAME_TITLE_TAGS[1] + TEXT_TYPE+COMMA_SEP+
                        COLUMN_NAME_TITLE_TAGS[2] + TEXT_TYPE+COMMA_SEP+
                        COLUMN_NAME_TITLE_TAGS[3] + TEXT_TYPE+COMMA_SEP+
                        COLUMN_NAME_TITLE_TAGS[4] + TEXT_TYPE+
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    }
}
