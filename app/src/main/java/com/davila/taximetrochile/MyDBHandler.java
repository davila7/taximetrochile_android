package com.davila.taximetrochile;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

/**
 * Created by danielavila on 29-03-16.
 */
public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "records.db";
    public static final String TABLE_RECORD = "records";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TO = "_to";
    public static final String COLUMN_FROM = "_from";
    public static final String COLUMN_FEE = "_fee";
    public static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_TO, COLUMN_FROM, COLUMN_FEE};

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_RECORD + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_TO + " TEXT , " +
                COLUMN_FROM + " TEXT , " +
                COLUMN_FEE + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORD );
        onCreate(db);

    }

    //Add new row to database
    public void addRecord(Record record){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TO, record.get_to());
        values.put(COLUMN_FROM, record.get_from());
        values.put(COLUMN_FEE, record.get_fee());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_RECORD, null, values);
        db.close();
    }

    //delete row from database
    public void deleteRecord(long id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RECORD + " WHERE " + COLUMN_ID + " = " + id + ";");
    }

    //print out database as a string
    public String[] arrayDataRecord(){

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_RECORD + " WHERE 1";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        String[] dbString = new String[c.getCount()];
        //Move to the firs row in your result
        c.moveToFirst();
        int i=0;
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("_to"))!= null){
                dbString[i] = c.getString(c.getColumnIndex("_to")) + " - " + c.getString(c.getColumnIndex("_from")) + "(" + c.getString(c.getColumnIndex("_fee")) + ")";
                i++;
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

    //get all rows
    public Cursor getAllRows(){
        String where = null;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(true, TABLE_RECORD, ALL_COLUMNS, where, null, null, null, null, null);

        if(c != null){
            c.moveToNext();
        }
        return c;
    }

    //Get a specific row (by ID)
    public Cursor getRow(long id){
        String where = COLUMN_ID + " = " + id;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(true, TABLE_RECORD, ALL_COLUMNS, where, null, null, null, null, null);

        if(c != null){
            c.moveToNext();
        }
        return c;
    }
}
