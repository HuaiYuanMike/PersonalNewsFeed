package com.example.mikehhsu.personalnewsfeed.network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mikehhsu.personalnewsfeed.db.Article;
import com.example.mikehhsu.personalnewsfeed.parser.NYTNewsListParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by mikehhsu on 10/10/16.
 */
// TODO: 10/10/16 The Result Generic Type should be modified based on the correct behavior
public class ArticlesFetchCommand extends AsyncTask<String, Void, Void> {
    InputStream inputStream = null;
    ArrayList<Article> articles = null;
    @Override
    protected Void doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
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
                // TODO: 11/1/16 Update the local DB
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
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        //UI thread

        if(articles != null && articles.size() > 0) {
            // TODO: 11/1/16 populate the UI thread
        }
        super.onPostExecute(aVoid);
    }
}
