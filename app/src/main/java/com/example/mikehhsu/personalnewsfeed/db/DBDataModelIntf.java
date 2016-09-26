package com.example.mikehhsu.personalnewsfeed.db;

import android.content.ContentValues;

/**
 * Created by mikehhsu on 9/23/16.
 */
public interface DBDataModelIntf {

    public String getTableName();
    public ContentValues getInsertContentValues();


    //rerion SQL query
    public String getQueryAllCommand();
    //endregion

    //// TODO: 9/23/16 more methonds to define our DBDataModelIntf interface behavior

}
