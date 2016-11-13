package com.example.mikehhsu.personalnewsfeed;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by mikehhsu on 11/5/16.
 */
public class MyApplication extends Application {
    private static MyApplication instance = null;

    public static MyApplication getInstance(){
        if(instance == null){
            Log.e("APPLICATION", "APPLICATION instance null!");
        }
        return instance;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
