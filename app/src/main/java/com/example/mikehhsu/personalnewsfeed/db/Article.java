package com.example.mikehhsu.personalnewsfeed.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.mikehhsu.personalnewsfeed.activity.MainActivity;

/**
 * Created by mikehhsu on 9/23/16.
 */
public final class Article implements SQLiteDBObject {

    private String guid = "";       //1
    private String author = "";     //2
    private String desc = "";       //3
    private String status = "";     //4
    private long time_stamp = 0;    //5
    private String title = "";      //6
    private String topic = "";      //7
    private String img = "";        //8
    private String url = "";        //9 // url to fetch detail article
    private String type = MainActivity.NewsListType.ALL.name();        //10 //type of the article to the user eq. unread, saved...etc

    public static final String TABLE_NAME = "article";
    public static final String COLUMN_NAME_GUID = "guid";               // 1
    public static final String COLUMN_NAME_AUTHOR = "author";           // 2
    public static final String COLUMN_NAME_DESC = "desc";               // 3
    public static final String COLUMN_NAME_STATUS = "status";           // 4
    public static final String COLUMN_NAME_TIME_STAMP = "time_stamp";   // 5
    public static final String COLUMN_NAME_TITLE = "title";             // 6
    public static final String COLUMN_NAME_TOPIC = "topic";             // 7
    public static final String COLUMN_NAME_IMAGE = "img";               // 8
    public static final String COLUMN_NAME_URL = "url";                 // 9
    public static final String COLUMN_NAME_TYPE = "type";                 // 9

    //default
    public Article(){

    }

    //regular
    public Article(String guid, String author, String desc, String status, long time_stamp, String title, String topic, String imgUrl)
    {
        this.guid = guid;
        this.author = author;
        this.desc = desc;
        this.status = status;
        this.time_stamp = time_stamp;
        this.title = title;
        this.topic = topic;
        this.img = imgUrl;
        this.url = imgUrl;
    }

    //cursor
    public Article(Cursor cursor)
    {
        this.guid = cursor.getString(1);
        this.author = cursor.getString(2);
        this.desc = cursor.getString(3);
        this.status = cursor.getString(4);
        this.time_stamp = cursor.getLong(5);
        this.title = cursor.getString(6);
        this.topic = cursor.getString(7);
        this.img = cursor.getString(8);
        this.url = cursor.getString(9);
        this.type = cursor.getString(10);
    }

    public static Article getDefaultInstance(){
        return new Article();
    }

    //region SQLiteDBObject Interface
    //Create Table
    @Override
    public String getSQLiteCreateTableCommand(){
        return "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER," +
                COLUMN_NAME_GUID + " TEXT PRIMARY KEY," +
                COLUMN_NAME_AUTHOR + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                COLUMN_NAME_DESC + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                COLUMN_NAME_STATUS + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                COLUMN_NAME_TIME_STAMP + NewsFeedDBHelper.INTEGER_TYPE + NewsFeedDBHelper.COMMA_SEP +
                COLUMN_NAME_TITLE + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                COLUMN_NAME_TOPIC + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                COLUMN_NAME_IMAGE + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                COLUMN_NAME_URL + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                COLUMN_NAME_TYPE + NewsFeedDBHelper.TEXT_TYPE + " )";
    }

    //Drop Table
    @Override
    public String getSQliteDropTableCommand(){
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_GUID, this.guid);
        contentValues.put(COLUMN_NAME_AUTHOR, this.author);
        contentValues.put(COLUMN_NAME_DESC, this.desc);
        contentValues.put(COLUMN_NAME_STATUS, this.status);
        contentValues.put(COLUMN_NAME_TIME_STAMP, this.time_stamp);
        contentValues.put(COLUMN_NAME_TITLE, this.title);
        contentValues.put(COLUMN_NAME_TOPIC, this.topic);
        contentValues.put(COLUMN_NAME_IMAGE, this.img);
        contentValues.put(COLUMN_NAME_URL, this.url);
        contentValues.put(COLUMN_NAME_TYPE, this.type);
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

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getGuid() {
        return guid;
    }

    public String getImg() {
        return img;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    //endregion
}
