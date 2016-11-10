package com.example.mikehhsu.personalnewsfeed.loeaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.example.mikehhsu.personalnewsfeed.broadcastReceiver.LoaderBroadcastReceiver;

/**
 * Created by mikehhsu on 9/29/16.
 */
public abstract class BaseAsyncTaskLoader<D> extends android.support.v4.content.AsyncTaskLoader<D> {

    //hold reference to the underlined data
    private D mData = null;

    private LoaderBroadcastReceiver observer = null;

    public BaseAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public void deliverResult(D data) {
        //new query result comes in
        //is the loader is in reset state, we don't deliver the data
        if(isReset()) {
            if(data != null) {
                releaseResource(data);
            }
            return;
        }

        D oldData = mData;
        mData = data;

        //if the loader is in start state, we use super.deliverResult() to deliver result to the client
        if(isStarted()){
            super.deliverResult(data);
        }

        if(oldData != null){
            releaseResource(oldData);
        }
    }

    //called by startLoading() <- LoaderManager in charge of calling this
    @Override
    protected void onStartLoading() {
        //if there is loaded data, we deliver it right away
        if(mData != null){
            deliverResult(mData);
        }

        //instantiate the observer
        if(observer == null){
            observer = new LoaderBroadcastReceiver(this);
            LocalBroadcastManager.getInstance(getContext())
                    .registerReceiver(observer, new IntentFilter(getReiverActionString()));
        }

        //force the loader to load in certain conditions
        if(takeContentChanged() || mData == null){
            forceLoad();
        }


    }

    @Override
    protected void onStopLoading() {

        cancelLoad();
        //we leave the observer as is.
        //because the loader in a stop state is still monitoring the data
        //once it is been started again it knows to load the changed data.
    }

    @Override
    protected void onReset() {
        super.onReset();
        //ensure the loader is in stop state
        onStopLoading();

        if(mData != null){
            releaseResource(mData);
            mData = null;
        }

        //unregister the observer
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(observer);
        observer = null;
    }

    //attempt to cancel the current async load
    @Override
    public void onCanceled(D data) {
        super.onCanceled(data);

        releaseResource(data);

    }



    /**
     * Helper function to take care of releasing resources associated
     * with an actively loaded data set.
     */
    // For a simple List, there is nothing to do. For something like a Cursor, we
    // would close it in this method. All resources associated with the Loader
    // should be released here.
    abstract public void releaseResource(D data);


    abstract String getReiverActionString();

}
