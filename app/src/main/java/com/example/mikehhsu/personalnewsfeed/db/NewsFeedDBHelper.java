package com.example.mikehhsu.personalnewsfeed.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mikehhsu on 9/17/16.
 */
public class NewsFeedDBHelper extends SQLiteOpenHelper {
    //once change the db schema, must increment the DB_VERSION
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NewsFeed.db";

    //Todo: Constructor - should be singleton?
    public NewsFeedDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //end of todo
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
