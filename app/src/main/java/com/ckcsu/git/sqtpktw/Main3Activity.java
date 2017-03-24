package com.ckcsu.git.sqtpktw;

/*public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EventBus.getDefault().register(this);

        Intent intent = getIntent();
        Thread thread = new Thread(new Runnable(){
            public void run(){
                FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getContext());
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    String title = "great";
                    String subtitle = "teet";
                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,title);
                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE,subtitle);
                    long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
                    SQLiteDatabase db1 = mDbHelper.getReadableDatabase();

                    // Define a projection that specifies which columns from the database
// you will actually use after this query.
                    String[] projection = {
                            FeedReaderContract.FeedEntry._ID,
                            FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                            FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
                    };

                    // Filter results WHERE "title" = 'My Title'
                    String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
                    String[] selectionArgs = { "great" };

                    // How you want the results sorted in the resulting Cursor
                    String sortOrder =
                            FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

                    Cursor c = db1.query(
                            FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                            projection,                               // The columns to return
                            selection,                                // The columns for the WHERE clause
                            selectionArgs,                            // The values for the WHERE clause
                            null,                                     // don't group the rows
                            null,                                     // don't filter by row groups
                            sortOrder                                 // The sort order
                    );
                    c.moveToFirst();
                    long itemId = c.getLong(
                            c.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID)
                    );

                EventBus.getDefault().post(new QueryEvent(itemId));


                }});


        thread.start();
        EventBus.getDefault().register(this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setvivi(){
    }

    @Override
    public void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

} */
