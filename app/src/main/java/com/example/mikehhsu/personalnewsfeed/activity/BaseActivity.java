package com.example.mikehhsu.personalnewsfeed.activity;

import android.app.Activity;

/**
 * Created by mikehhsu on 6/25/16.
 */
public abstract class BaseActivity extends Activity {
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(getLayoutResID());
    }
    abstract int getLayoutResID();
}
