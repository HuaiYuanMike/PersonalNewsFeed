package com.example.mikehhsu.personalnewsfeed.network;

import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by mikehhsu on 11/18/16.
 */
public abstract class HttpGetCommand extends AsyncTask<String, Void, Void> {
    InputStream inputStream = null;
    String url = "";
    final int READ_TIMEOUT = 5000;
    final int CONNECT_TIMEOUT = 5000;
    final String REQUEST_METHOD = "GET";

    HttpURLConnection httpURLConnection = null;

    @Override
    protected Void doInBackground(String... urls) {
//        try {
//            this.url = urls[0];
//            URL url = new URL(this.url);
//            httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setReadTimeout(READ_TIMEOUT);
//            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);//might throw SocketTimeoutException
//            httpURLConnection.setRequestMethod(REQUEST_METHOD);//optional - GET is the default action
//            httpURLConnection.setDoInput(true);
//            httpURLConnection.connect();
//            Log.d(HttpGetCommand.class.toString(), "Response Code: " + httpURLConnection.getResponseCode() + httpURLConnection.getResponseMessage() + httpURLConnection.getHeaderField("Location"));
//            //for the 303 See Other response
//            if(httpURLConnection.getResponseCode() == 303 && httpURLConnection.getHeaderField("Location") != null){
//                url = new URL(httpURLConnection.getHeaderField("Location"));
//                httpURLConnection.disconnect();
//                httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.connect();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally{
//            if (inputStream != null){
//                inputStream = null;
//            }
//        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
