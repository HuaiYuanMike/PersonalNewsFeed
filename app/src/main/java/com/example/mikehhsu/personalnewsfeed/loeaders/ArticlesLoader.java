package com.example.mikehhsu.personalnewsfeed.loeaders;

import android.content.Context;

import com.example.mikehhsu.personalnewsfeed.db.Article;

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
        return null;
    }
}
