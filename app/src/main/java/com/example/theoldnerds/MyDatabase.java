package com.example.theoldnerds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ComicLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_Library";

    private static final String COLUMN_ID = "comic_id";
    private static final String COLUMN_COMIC_NAME = "comic_name";
    private static final String COLUMN_COMIC_DESCRIPTION = "comic_description";
    private static final String COLUMN_COMIC_CATEGORY = "comic_category";
    private static final String COLUMN_COMIC_DATE = "acquisition_date";

    MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COMIC_NAME + " TEXT, " +
                COLUMN_COMIC_DESCRIPTION + " TEXT, " +
                COLUMN_COMIC_DATE + " TEXT, " +
                COLUMN_COMIC_CATEGORY + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addComic(String name, String description, String date, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_COMIC_NAME,name);
        cv.put(COLUMN_COMIC_DESCRIPTION, description);
        cv.put(COLUMN_COMIC_DATE, date);
        cv.put(COLUMN_COMIC_CATEGORY, category);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
                if(db != null){
                    cursor = db.rawQuery(query, null);
                }
                return cursor;
    }

    void updateData(String row_id, String name, String description, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvs = new ContentValues();

        cvs.put(COLUMN_COMIC_NAME, name);
        cvs.put(COLUMN_COMIC_DESCRIPTION, description);
        cvs.put(COLUMN_COMIC_DATE, date);

       long result = db.update(TABLE_NAME, cvs, "comic_id=?", new String[]{row_id});

       if (result == -1){
           Toast.makeText(context, "Failed! ", Toast.LENGTH_SHORT).show();
       }
       else{
           Toast.makeText(context, "Successfully Updated! ", Toast.LENGTH_SHORT).show();
       }
    }

}
