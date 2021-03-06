package com.example.mikehhsu.personalnewsfeed.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by mikehhsu on 9/17/16.
 */
public class NewsFeedDBHelper extends SQLiteOpenHelper {
    //once change the db schema, must increment the DB_VERSION
//    public static final int DATABASE_VERSION = 6; // Added ArticleDetail Table
//    public static final int DATABASE_VERSION = 7; // Added url field to Article Table
                                                  // for detail article fetching
    public static final int DATABASE_VERSION = 8; // Added type field to Article Table

    public static final String DATABASE_NAME = "NewsFeed.db";

    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";

    private static NewsFeedDBHelper instance;

    public static NewsFeedDBHelper getInstance(Context context){
        if(instance == null){
            return new NewsFeedDBHelper(context);
        }
        return instance;
    }

    private NewsFeedDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Article.getDefaultInstance().getSQLiteCreateTableCommand());
        db.execSQL(ArticleDetail.getDefaultInstance().getSQLiteCreateTableCommand());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Article.getDefaultInstance().getSQliteDropTableCommand());
        db.execSQL(ArticleDetail.getDefaultInstance().getSQliteDropTableCommand());
        onCreate(db);
    }

    //CRUD
    //// TODO: 9/23/16 more CRUD methods
    public long insertOrUpdate(SQLiteDBObject dbTableObj){
        return this.getWritableDatabase().
                insertWithOnConflict(dbTableObj.getTableName(), null ,
                        dbTableObj.getInsertContentValues(), SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void insertOrUpdateAll(ArrayList<SQLiteDBObject> dbTableObjList){
        SQLiteDatabase db = this.getWritableDatabase();
        for (SQLiteDBObject dbTableObj : dbTableObjList){
            db.insertWithOnConflict(dbTableObj.getTableName(), null ,
                    dbTableObj.getInsertContentValues(), SQLiteDatabase.CONFLICT_REPLACE);
        }
        db.close();
    }

    public long insertOrUpdateRow(SQLiteDBObject dbTableObj){
        return this.getWritableDatabase().
                insertWithOnConflict(dbTableObj.getTableName(), null ,
                        dbTableObj.getInsertContentValues(), SQLiteDatabase.CONFLICT_REPLACE);
    }

    public Cursor queryAll(SQLiteDBObject dbTableObj){
        return this.getReadableDatabase().rawQuery(dbTableObj.getQueryAllCommand(), null);
    }

    public Cursor query(String rawQueryStr, String[] selectionArgs){
        return this.getReadableDatabase().rawQuery(rawQueryStr, selectionArgs);
    }

//    public void close(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        if(db != null && db.isOpen()){
//            db.close();
//        }
//    }

    //// TODO: 9/25/16 should be in a different thread (How to do this)
//    public SQLiteDatabase getWritableDBInBackground(){
//        if(db == null || db.isReadOnly()){
//            if(db != null && db.isOpen()) {
//                this.close();
//            }
//            return this.getWritableDatabase();
//        }
//        return db;
//    }
//    //getReadableDBInBackground()
//    public SQLiteDatabase getReadableDBInBackground(){
//        if(db == null){
//            return this.getReadableDatabase();
//        }
//        return db;
//    }

}
