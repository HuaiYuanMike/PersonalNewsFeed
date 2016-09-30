package com.example.mikehhsu.personalnewsfeed.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.mikehhsu.personalnewsfeed.loeaders.BaseAsyncTaskLoader;

/**
 * Created by mikehhsu on 9/29/16.
 */
public class LoaderBroadcastReceiver extends BroadcastReceiver {
    private BaseAsyncTaskLoader loader;
    public LoaderBroadcastReceiver(BaseAsyncTaskLoader loader){
        this.loader = loader;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        loader.onContentChanged();
    }
}
