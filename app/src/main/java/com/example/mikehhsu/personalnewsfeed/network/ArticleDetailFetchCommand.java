package com.example.mikehhsu.personalnewsfeed.network;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.mikehhsu.personalnewsfeed.MyApplication;
import com.example.mikehhsu.personalnewsfeed.db.NewsFeedDBHelper;
import com.example.mikehhsu.personalnewsfeed.loeaders.ArticlesLoader;
import com.example.mikehhsu.personalnewsfeed.parser.NYTNewsListParser;

/**
 * Created by mikehhsu on 11/18/16.
 */
public class ArticleDetailFetchCommand extends HttpGetCommand {
    String articleDetailRaw = "";

    @Override
    protected Void doInBackground(String... urls) {
        super.doInBackground();// make connection through Http Get
        try {
            if(httpURLConnection == null){
                return null;
            }
            inputStream = httpURLConnection.getInputStream();
            if(inputStream != null){
//                articles = new NYTNewsListParser().parse(inputStream);
//                NewsFeedDBHelper.getInstance(MyApplication.getInstance().getApplicationContext())
//                        .insertOrUpdateAll(articles);
//                InputStreamReader reader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(reader);
//                StringBuilder html = new StringBuilder();
//                for (String line; (line = bufferedReader.readLine()) != null; ) {
//                    html.append(line);
//                }
//                inputStream.close();
////                char[] buffer = new char[500];
////                reader.read(buffer);
////                Log.d(ArticlesFetchCommand.class.toString(), "Input Stream: " + new String(buffer));
//                Log.d(ArticlesFetchCommand.class.toString(), "Input Stream: " + html.toString());
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
        if(articleDetailRaw.length() > 0) {
//            LocalBroadcastManager.getInstance(MyApplication.getInstance().getApplicationContext()).sendBroadcast(new Intent(ArticlesLoader.getBroadcastString()));
        }
        super.onPostExecute(aVoid);
    }

}
