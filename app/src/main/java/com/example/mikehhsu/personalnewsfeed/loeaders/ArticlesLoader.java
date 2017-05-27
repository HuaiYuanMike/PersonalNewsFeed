package com.example.mikehhsu.personalnewsfeed.loeaders;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.mikehhsu.personalnewsfeed.activity.MainActivity;
import com.example.mikehhsu.personalnewsfeed.db.Article;
import com.example.mikehhsu.personalnewsfeed.db.NewsFeedDBHelper;

import java.util.ArrayList;

/**
 * Created by mikehhsu on 10/1/16.
 */
public class ArticlesLoader extends BaseAsyncTaskLoader<ArrayList<Article>> {

    public final static int ARTICLES_LOADER_ID = 1000;

    MainActivity.NewsListType type = null;// the news article type that this loader needs to load

    public ArticlesLoader(Context context, MainActivity.NewsListType type){
        super(context);
        this.type = type;
    }

    @Override
    public void releaseResource(ArrayList<Article> data) {
        data.clear();
    }

    @Override
    protected void onReset() {
        super.onReset();
        this.type = null;
    }

    public static String getBroadcastString() {
        return "articles_loader_receiver";
    }

    public String getReiverActionString() {
        return "articles_loader_receiver";
    }

    @Override
    public ArrayList<Article> loadInBackground() {
        ArrayList<Article> articles = new ArrayList<>();

        Log.d(this.toString(), "Load in background!");

        String queryString = (this.type == null) ? "SELECT * FROM " + Article.TABLE_NAME :
                "SELECT * FROM " + Article.TABLE_NAME + " WHERE type = \'" + this.type.name() + "\'";
        Cursor cursor = NewsFeedDBHelper.getInstance(getContext()).query(queryString, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast())
        {
            articles.add(new Article(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        NewsFeedDBHelper.getInstance(getContext()).close();
        return articles;
    }
}
