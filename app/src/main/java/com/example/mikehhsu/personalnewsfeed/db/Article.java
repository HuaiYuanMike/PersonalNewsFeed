package com.example.mikehhsu.personalnewsfeed.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

/**
 * Created by mikehhsu on 9/23/16.
 */
public final class Article implements DBDataModelIntf{

    private String author = "";     // 1
    private String desc = "";       // 2
    private String status = "";     // 3
    private long time_stamp = 0;   // 4
    private String title = "";      // 5
    private String topic = "";      // 6

    public static final class Contract implements BaseColumns{
        public static final String TABLE_NAME = "article";
        public static final String COLUMN_NAME_AUTHOR = "author";           // 1
        public static final String COLUMN_NAME_DESC = "desc";               // 2
        public static final String COLUMN_NAME_STATUS = "status";           // 3
        public static final String COLUMN_NAME_TIME_STAMP = "time_stamp";   // 4
        public static final String COLUMN_NAME_TITLE = "title";             // 5
        public static final String COLUMN_NAME_TOPIC = "topic";             // 6
    }

    //default
    public Article(){

    }

    //regular
    public Article(String author, String desc, String status, long time_stamp, String title, String topic)
    {
        this.author = author;
        this.desc = desc;
        this.status = status;
        this.time_stamp = time_stamp;
        this.title = title;
        this.topic = topic;
    }

    //cursor
    public Article(Cursor cursor)
    {
        this.author = cursor.getString(1);
        this.desc = cursor.getString(2);
        this.status = cursor.getString(3);
        this.time_stamp = cursor.getLong(4);
        this.title = cursor.getString(5);
        this.topic = cursor.getString(6);
    }

    //region SQL Command
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Contract.TABLE_NAME + " (" +
                    Contract._ID + " INTEGER PRIMARY KEY," +
                    Contract.COLUMN_NAME_AUTHOR + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                    Contract.COLUMN_NAME_DESC + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                    Contract.COLUMN_NAME_STATUS + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                    Contract.COLUMN_NAME_TIME_STAMP + NewsFeedDBHelper.INTEGER_TYPE + NewsFeedDBHelper.COMMA_SEP +
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


    public long getTime_stamp() {
        return time_stamp;
    }


    //region getters and setters

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime_stamp(long time_stamp) {
        this.time_stamp = time_stamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAuthor() {
        return author;
    }

    public String getDesc() {
        return desc;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getTopic() {
        return topic;
    }

    //endregion
}
