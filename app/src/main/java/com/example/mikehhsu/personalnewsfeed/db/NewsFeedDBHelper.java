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

    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";

    private static SQLiteDatabase db = null;

    //Todo: Constructor - should be singleton?
    public NewsFeedDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //end of todo
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Article.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Article.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    //CRUD
    //// TODO: 9/23/16 more CRUD methods
    public long insert(DBDataModelIntf dbObj){
        if(db == null || db.isReadOnly()){
            db = this.getWritableDBInBackground();
        }

        return db.insert(dbObj.getTableName(), null , dbObj.getInsertContentValues());
    }

    //// TODO: 9/23/16 should be in a different thread
    public SQLiteDatabase getWritableDBInBackground(){
        return this.getWritableDatabase();
    }
    //getReadableDBInBackground()

}
