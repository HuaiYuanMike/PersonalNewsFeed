package com.example.mikehhsu.personalnewsfeed.network;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.mikehhsu.personalnewsfeed.MyApplication;
import com.example.mikehhsu.personalnewsfeed.db.Article;
import com.example.mikehhsu.personalnewsfeed.db.DBDataModelIntf;
import com.example.mikehhsu.personalnewsfeed.db.NewsFeedDBHelper;
import com.example.mikehhsu.personalnewsfeed.loeaders.ArticlesLoader;
import com.example.mikehhsu.personalnewsfeed.parser.NYTNewsListParser;
import java.util.ArrayList;

/**
 * Created by mikehhsu on 10/10/16.
 */
// TODO: 10/10/16 The Result Generic Type should be modified based on the correct behavior
public class ArticlesFetchCommand extends HttpGetCommand {
    ArrayList<DBDataModelIntf> articles = null;

    @Override
    protected Void doInBackground(String... urls) {
        super.doInBackground();// make connection through Http Get
        try {
            if(httpURLConnection == null){
                return null;
            }
            inputStream = httpURLConnection.getInputStream();
            if(inputStream != null){
                articles = new NYTNewsListParser().parse(inputStream);
                NewsFeedDBHelper.getInstance(MyApplication.getInstance().getApplicationContext())
                .insertOrUpdateAll(articles);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if (inputStream != null){
                inputStream = null;
            }
            NewsFeedDBHelper.getInstance(MyApplication.getInstance().getApplicationContext()).close();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        //UI thread

        if(articles != null && articles.size() > 0) {
            LocalBroadcastManager.getInstance(MyApplication.getInstance().getApplicationContext()).sendBroadcast(new Intent(ArticlesLoader.getBroadcastString()));
        }
        super.onPostExecute(aVoid);
    }
}
