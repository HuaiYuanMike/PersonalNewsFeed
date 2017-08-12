package com.example.mikehhsu.personalnewsfeed.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by mikehhsu on 11/16/16.
 */
public class ImageUrlFetchCommand extends AsyncTask<String, String, Bitmap> {

    ImageView imageView = null;
    Context context = null;

    public ImageUrlFetchCommand(Context context, ImageView view){
        this.imageView = view;
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        if(context == null){
            Log.e(this.getClass().toString(), "Context is null, no command was executed!");
            return null;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        InputStream inputStream = null;
        Bitmap bitmap = null;
        if(networkInfo != null && networkInfo.isConnected()) {
            try{
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(9000);
                urlConnection.setConnectTimeout(9000);//might throw SocketTimeoutException
                urlConnection.setRequestMethod("GET");//optional - GET is the default action
                urlConnection.setDoInput(true);
                urlConnection.connect();
                Log.d(ArticlesFetchCommand.class.toString(), "Response Code: " + urlConnection.getResponseCode());
                inputStream = urlConnection.getInputStream();
                if(inputStream != null){
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                }
                urlConnection.disconnect();
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (ProtocolException p){
                p.printStackTrace();
            }catch (IOException i){
                i.printStackTrace();
            }finally {
                return bitmap;
            }
        }else {
            Log.e(this.getClass().toString(), "Network connection not available!");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(imageView != null){
            imageView.setImageBitmap(bitmap);
            imageView.setBackgroundResource(0);
        }
    }
}
