package com.example.mikehhsu.personalnewsfeed.db;

import android.provider.BaseColumns;

/**
 * Created by mikehhsu on 9/17/16.
 */
public final class NewsFeedContract {
    //To prevent someone from accidentally instantiating the contract class,
    //make the constructor private
    private NewsFeedContract(){}

    //Table "article"
    public static final class Article implements BaseColumns{
        public static final String TABLE_NAME = "article";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESC = "desc";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_TOPIC = "topic";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_TIME_STAMP = "time_stamp";

    }


}
