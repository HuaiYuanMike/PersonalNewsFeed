package com.example.mikehhsu.personalnewsfeed.network;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.mikehhsu.personalnewsfeed.MyApplication;
import com.example.mikehhsu.personalnewsfeed.db.ArticleDetail;
import com.example.mikehhsu.personalnewsfeed.db.NewsFeedDBHelper;
import com.example.mikehhsu.personalnewsfeed.loeaders.ArticlesLoader;
import com.example.mikehhsu.personalnewsfeed.parser.NYTNewsListParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mikehhsu on 11/18/16.
 */
public class ArticleDetailFetchCommand extends HttpGetCommand {
    ArticleDetail articleDetail = null;

    @Override
    protected Void doInBackground(String... urls) {
        try {
            this.url = urls[0];
            Log.d(this.getClass().toString(), "Jsoup connect to remote: " + this.url);
            Document doc = Jsoup.connect(this.url).get();
            Log.d(this.getClass().toString(), "title: " + doc.title());
            Log.d(this.getClass().toString(), "content size: " + doc.getElementsByTag("p").size());

            StringBuilder stringBuilder = new StringBuilder();
            for(Element element : doc.getElementsByAttributeValue("class", "story-body-text story-content")){
                stringBuilder.append(element.ownText() + "\n");
//                Log.d(getClass().toString(), element.ownText());
            }
            Log.d(getClass().toString(), "Article Content: " + stringBuilder.toString());
            articleDetail = new ArticleDetail();
            articleDetail.setLink(this.url);
            articleDetail.setTitle(doc.title());
            articleDetail.setDetail(stringBuilder.toString());
            articleDetail.setGuid(urls[1]);
            articleDetail.setImg(doc.getElementsByTag("img").attr("src"));
            // TODO: 12/1/16 set author
            //// TODO: 12/1/16 do insertOrUpdate ArticleDetail table ?
// URL url = new URL(this.url);
//            httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setReadTimeout(READ_TIMEOUT);
//            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);//might throw SocketTimeoutException
//            httpURLConnection.setRequestMethod(REQUEST_METHOD);//optional - GET is the default action
//            httpURLConnection.setDoInput(true);
//            httpURLConnection.connect();
//            Log.d(ArticleDetailFetchCommand.class.toString(), "Response Code: " + httpURLConnection.getResponseCode() + httpURLConnection.getResponseMessage() + httpURLConnection.getHeaderField("Location"));
//            //for the 303 See Other response
//            if(httpURLConnection.getResponseCode() == 303 && httpURLConnection.getHeaderField("Location") != null){
//                url = new URL(httpURLConnection.getHeaderField("Location"));
//                httpURLConnection.disconnect();
//                httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.connect();
//            }
//            inputStream = httpURLConnection.getInputStream();
//            if(inputStream != null){
//
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
//            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if (inputStream != null){
                inputStream = null;
            }
//            NewsFeedDBHelper.getInstance(MyApplication.getInstance().getApplicationContext()).close();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        //UI thread
        if(articleDetail != null) {
//            LocalBroadcastManager.getInstance(MyApplication.getInstance().getApplicationContext()).sendBroadcast(new Intent(ArticlesLoader.getBroadcastString()));
        }
        super.onPostExecute(aVoid);
    }

}
