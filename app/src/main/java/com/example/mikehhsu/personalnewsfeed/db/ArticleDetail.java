package com.example.mikehhsu.personalnewsfeed.db;

import android.content.ContentValues;

/**
 * Created by mikehhsu on 11/23/16.
 */
public class ArticleDetail implements SQLiteDBTable {

    public final static String SQL_CREATE_ENTRIES = "";

    public class Contract implements SQLiteDBTable.Contract{
        public static final String TABLE_NAME = "article_detail";
        public static final String COLUMN_NAME_GUID = "guid";               // 1
        public static final String COLUMN_NAME_TITLE = "title";             // 2
        public static final String COLUMN_NAME_AUTHOR = "author";           // 3
        public static final String COLUMN_NAME_DETAIL = "detail";           // 3
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public ContentValues getInsertContentValues() {
        return null;
    }

    @Override
    public String getQueryAllCommand() {
        return null;
    }
}
