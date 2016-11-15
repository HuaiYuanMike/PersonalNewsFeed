package com.example.mikehhsu.personalnewsfeed.network;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.mikehhsu.personalnewsfeed.MyApplication;
import com.example.mikehhsu.personalnewsfeed.db.Article;
import com.example.mikehhsu.personalnewsfeed.db.DBDataModelIntf;
import com.example.mikehhsu.personalnewsfeed.db.NewsFeedDBHelper;
import com.example.mikehhsu.personalnewsfeed.loeaders.ArticlesLoader;
import com.example.mikehhsu.personalnewsfeed.parser.NYTNewsListParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by mikehhsu on 10/10/16.
 */
// TODO: 10/10/16 The Result Generic Type should be modified based on the correct behavior
public class ArticlesFetchCommand extends AsyncTask<Void, Void, Void> {
    InputStream inputStream = null;
    ArrayList<DBDataModelIntf> articles = null;

    private final String urlNYT = "http://rss.nytimes.com/services/xml/rss/nyt/Americas.xml";

    @Override
    protected Void doInBackground(Void... urls) {
        try {
            URL url = new URL(urlNYT);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(9000);
            urlConnection.setConnectTimeout(9000);//might throw SocketTimeoutException
            urlConnection.setRequestMethod("GET");//optional - GET is the default action
            urlConnection.setDoInput(true);
            urlConnection.connect();
            Log.d(ArticlesFetchCommand.class.toString(), "Response Code: " + urlConnection.getResponseCode());
            inputStream = urlConnection.getInputStream();

            if(inputStream != null){
                articles = new NYTNewsListParser().parse(inputStream);
                NewsFeedDBHelper.getInstance(MyApplication.getInstance().getApplicationContext())
                .insertOrUpdateAll(articles);
                // convert inputSream type data into String
//                Reader reader = null;
//                reader = new InputStreamReader(inputStream, "UTF-8");
//                char[] buffer = new char[500];
//                reader.read(buffer);
//                Log.d(ArticlesFetchCommand.class.toString(), "Input Stream: " + new String(buffer));
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
