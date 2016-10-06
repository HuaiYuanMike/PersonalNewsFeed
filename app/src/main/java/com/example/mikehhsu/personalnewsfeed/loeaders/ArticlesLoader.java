package com.example.mikehhsu.personalnewsfeed.loeaders;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.mikehhsu.personalnewsfeed.db.Article;
import com.example.mikehhsu.personalnewsfeed.db.NewsFeedDBHelper;

import java.util.ArrayList;

/**
 * Created by mikehhsu on 10/1/16.
 */
public class ArticlesLoader extends BaseAsyncTaskLoader<ArrayList<Article>> {

    public ArticlesLoader(Context context){
        super(context);
    }

    @Override
    public void releaseResource(ArrayList<Article> data) {
        data.clear();
        data = null;
    }

    @Override
    String getBroadcastString() {
        return "articles_loader_receiver";
    }

    @Override
    public ArrayList<Article> loadInBackground() {
        ArrayList<Article> articles = new ArrayList<>();
        Cursor cursor = NewsFeedDBHelper.getInstance(getContext()).query("SELECT * FROM " + Article.Contract.TABLE_NAME, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            articles.add(new Article(cursor));
            Log.d("mikelog", "article " + (articles.size() - 1) + ", time stamp - " + articles.get(articles.size() - 1).getTime_stamp());
            cursor.moveToNext();
        }
        return articles;
    }
}
