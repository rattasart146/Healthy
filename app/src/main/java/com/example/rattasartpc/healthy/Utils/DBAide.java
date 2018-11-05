package com.example.rattasartpc.healthy.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.rattasartpc.healthy.Sleep.sleepAsset;

import java.util.ArrayList;

public class DBAide extends SQLiteOpenHelper{

    private SQLiteDatabase sqLiteDatabase;

    public DBAide(Context context){
        super(context, sleepAsset.DATABASE_NAME, null, sleepAsset.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_FRIEND_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                sleepAsset.TABLE,
                sleepAsset.Column.ID,
                sleepAsset.Column.DATE,
                sleepAsset.Column.WAKE_UP_TIME,
                sleepAsset.Column.SLEEP_TIME,
                sleepAsset.Column.USER_ID);

        db.execSQL(CREATE_FRIEND_TABLE);
        Log.d("Database Aide", "THE DATABASE WAS CREATED");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS " + sleepAsset.TABLE;

        db.execSQL(DROP_FRIEND_TABLE);

        onCreate(db);

    }

    public void updateSleep(sleepAsset sleep){

        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(sleepAsset.Column.DATE, sleep.getDate());
        values.put(sleepAsset.Column.WAKE_UP_TIME, sleep.getWakeUpTime());
        values.put(sleepAsset.Column.SLEEP_TIME, sleep.getSleepTime());
        values.put(sleepAsset.Column.USER_ID, sleep.getUserId());

        int row = sqLiteDatabase.update(sleepAsset.TABLE,
                values,
                sleepAsset.Column.ID + " = ?",
                new String[] { String.valueOf(sleep.getId()) });

        sqLiteDatabase.close();

    }

    public void addSleep(sleepAsset sleep){

        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(sleepAsset.Column.DATE, sleep.getDate());
        values.put(sleepAsset.Column.WAKE_UP_TIME, sleep.getWakeUpTime());
        values.put(sleepAsset.Column.SLEEP_TIME, sleep.getSleepTime());
        values.put(sleepAsset.Column.USER_ID, sleep.getUserId());

        try {
            sqLiteDatabase.insert(sleepAsset.TABLE, null, values);
            Log.d("Database Aide", "Inserted");
        }catch (SQLiteConstraintException e){
            Log.d("Database Aide", e.getLocalizedMessage());
        }

        sqLiteDatabase.close();

    }

    public ArrayList<sleepAsset> getSleepList(String userId){
        ArrayList<sleepAsset> sleeps = new ArrayList<sleepAsset>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from sleepWithMultiUsers where userId = ?", new String[] {userId});

        while (cursor.moveToNext()){
            int _id = cursor.getInt(0);
            String _date = cursor.getString(1);
            String _wakeUpTime = cursor.getString(2);
            String _sleepTime = cursor.getString(3);
            String _userId = cursor.getString(4);

            sleepAsset sleep = new sleepAsset(_date, _wakeUpTime, _sleepTime, _id, _userId);
            sleeps.add(sleep);

        }

        cursor.close();
        sqLiteDatabase.close();

        return sleeps;

    }
}