package com.example.mikehhsu.personalnewsfeed.network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mikehhsu.personalnewsfeed.parser.NYTNewsListParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mikehhsu on 10/10/16.
 */
// TODO: 10/10/16 The Result Generic Type should be modified based on the correct behavior
public class ArticlesFetchCommand extends AsyncTask<String, Void, Void> {
    InputStream inputStream = null;
    @Override
    protected Void doInBackground(String... urls) {
        //URl object
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(10000);//throws SocketTimeoutException
            urlConnection.setRequestMethod("GET");//optionally
            urlConnection.setDoInput(true);

            urlConnection.connect();
            Log.d(ArticlesFetchCommand.class.toString(), "Response Code: " + urlConnection.getResponseCode());

            inputStream = urlConnection.getInputStream();
            // TODO: 10/10/16 correct Action to parse the fetched data need to be implemented
            if(inputStream != null){
                // parse the XML file from inputStream
                new NYTNewsListParser().parse(inputStream);
                //
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
        super.onPostExecute(aVoid);
    }
}
