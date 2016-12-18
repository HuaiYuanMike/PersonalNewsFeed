package com.example.mikehhsu.personalnewsfeed.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcelable;
import android.provider.BaseColumns;
import android.provider.SyncStateContract;

import com.example.mikehhsu.personalnewsfeed.fragment.BaseFragment;

import java.io.BufferedReader;

/**
 * Created by mikehhsu on 9/23/16.
 */
public interface SQLiteDBObject {

    interface Contract extends BaseColumns{}

    String getTableName();
    ContentValues getInsertContentValues();

    //rerion SQL commands
    String getSQLiteCreateTableCommand();
    String getSQliteDropTableCommand();
    String getQueryAllCommand();
    //endregion

    //// TODO: 9/23/16 more methonds to define our SQLiteDBObject interface behavior

}
