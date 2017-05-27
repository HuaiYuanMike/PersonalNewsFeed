package com.example.mikehhsu.personalnewsfeed.db;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by mikehhsu on 11/23/16.
 */
public class ArticleDetail implements SQLiteDBObject {

    public final static String SQL_CREATE_ENTRIES = "";

    private String guid ="";   //1
    private String author =""; //2
    private String title ="";  //3
    private String detail =""; //4
    private String link ="";   //5
    private String img ="";   //6

    public static final String TABLE_NAME = "article_detail";
    public static final String COLUMN_NAME_GUID = "guid";               // 1
    public static final String COLUMN_NAME_AUTHOR = "author";           // 2
    public static final String COLUMN_NAME_TITLE = "title";             // 3
    public static final String COLUMN_NAME_DETAIL = "detail";           // 4
    public static final String COLUMN_NAME_LINK = "link";               // 5
    public static final String COLUMN_NAME_IMG = "img";                // 6


    //default
    public ArticleDetail(){

    }

    public ArticleDetail(Cursor cursor){
        this.guid = cursor.getString(1);
        this.author = cursor.getString(2);
        this.title = cursor.getString(3);
        this.detail = cursor.getString(4);
        this.link = cursor.getString(5);
        this.img = cursor.getString(6);
    }

    public static ArticleDetail getDefaultInstance(){
        return new ArticleDetail();
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
        contentValues.put(COLUMN_NAME_TITLE, this.title);
        contentValues.put(COLUMN_NAME_DETAIL, this.detail);
        contentValues.put(COLUMN_NAME_LINK, this.link);
        contentValues.put(COLUMN_NAME_IMG, this.img);
        return contentValues;
    }

    @Override
    public String getSQLiteCreateTableCommand() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER," +
                COLUMN_NAME_GUID + " TEXT PRIMARY KEY," +
                COLUMN_NAME_AUTHOR + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                COLUMN_NAME_TITLE + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                COLUMN_NAME_DETAIL + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                COLUMN_NAME_LINK + NewsFeedDBHelper.TEXT_TYPE + NewsFeedDBHelper.COMMA_SEP +
                COLUMN_NAME_IMG + NewsFeedDBHelper.TEXT_TYPE + " )";
    }

    @Override
    public String getSQliteDropTableCommand() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    @Override
    public String getQueryAllCommand() {
        return "SELECT * FROM " + this.getTableName();
    }

    //region getter and setters

    public String getGuid() {
        return guid;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getLink() {
        return link;
    }

    public String getImg() {
        return img;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setImg(String img) {
        this.img = img;
    }

    //endredion
}
