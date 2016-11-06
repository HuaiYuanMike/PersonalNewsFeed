package com.example.mikehhsu.personalnewsfeed;

import android.app.Application;

/**
 * Created by mikehhsu on 11/5/16.
 */
public class MyApplication extends Application {
    private static MyApplication instance = null;
    private MyApplication(){
        super();
    }

    public static MyApplication getInstance(){
        if(instance == null){
            instance = new MyApplication();
        }
        return instance;
    }
}
