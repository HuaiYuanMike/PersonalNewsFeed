package com.example.mikehhsu.personalnewsfeed.db;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Created by mikehhsu on 9/23/16.
 */
public final class Article implements DBDataModelIntf{

    private String title = "";
    private String desc = "";
    private String author = "";
    private String topic = "";
    private String status = "";
    private String time_stamp = "";

    public static final class Contract implements BaseColumns{
        public static final String TABLE_NAME = "article";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESC = "desc";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_TOPIC = "topic";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_TIME_STAMP = "time_stamp";
    }

    //region SQL Command
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Contract.TABLE_NAME + " (" +
                    Contract._ID + " INTEGER PRIMARY KEY," +
                    Contract.COLUMN_NAME_AUTHOR + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                    Contract.COLUMN_NAME_DESC + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                    Contract.COLUMN_NAME_STATUS + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                    Contract.COLUMN_NAME_TIME_STAMP + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                    Contract.COLUMN_NAME_TITLE + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                    Contract.COLUMN_NAME_TOPIC + NewsFeedDBHelper.TEXT_TYPE + " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Contract.TABLE_NAME;

    //endregion

    //region DBDateModelIntf
    @Override
    public String getTableName() {
        return Contract.TABLE_NAME;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.COLUMN_NAME_AUTHOR, this.author);
        contentValues.put(Contract.COLUMN_NAME_DESC, this.desc);
        contentValues.put(Contract.COLUMN_NAME_STATUS, this.status);
        contentValues.put(Contract.COLUMN_NAME_TIME_STAMP, this.time_stamp);
        contentValues.put(Contract.COLUMN_NAME_TITLE, this.title);
        contentValues.put(Contract.COLUMN_NAME_TOPIC, this.topic);
        return contentValues;
    }

    @Override
    public String getQueryAllCommand() {
        return "SELECT * FROM " + this.getTableName();
    }

    //endregion

}
