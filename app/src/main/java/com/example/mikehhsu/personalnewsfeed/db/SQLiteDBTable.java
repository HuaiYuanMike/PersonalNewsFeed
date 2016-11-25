package com.example.mikehhsu.personalnewsfeed.db;

import android.content.ContentValues;
import android.os.Parcelable;
import android.provider.BaseColumns;
import android.provider.SyncStateContract;

import com.example.mikehhsu.personalnewsfeed.fragment.BaseFragment;

import java.io.BufferedReader;

/**
 * Created by mikehhsu on 9/23/16.
 */
public interface SQLiteDBTable {

    String getTableName();
    ContentValues getInsertContentValues();

    interface Contract extends BaseColumns{}

    //rerion SQL query
    String getQueryAllCommand();
    //endregion

    //// TODO: 9/23/16 more methonds to define our SQLiteDBTable interface behavior

}
