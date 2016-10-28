package com.example.mikehhsu.personalnewsfeed.activity;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;

import com.example.mikehhsu.personalnewsfeed.R;
import com.example.mikehhsu.personalnewsfeed.db.Article;
import com.example.mikehhsu.personalnewsfeed.db.NewsFeedDBHelper;
import com.example.mikehhsu.personalnewsfeed.fragment.MainPagerFragment;
import com.example.mikehhsu.personalnewsfeed.loeaders.ArticlesLoader;
import com.example.mikehhsu.personalnewsfeed.network.ArticlesFetchCommand;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //test create local db with a test article data entry
        // TODO: 9/29/16 insert of entries should be put where we got data from the server
        //region test
        NewsFeedDBHelper dbHelper = NewsFeedDBHelper.getInstance(this);
        dbHelper.insertOrUpdate(new Article("", "", "", System.currentTimeMillis(), "", ""));
        dbHelper.insertOrUpdate(new Article("", "", "", System.currentTimeMillis() + 2, "", ""));
        dbHelper.close();

        //endregion

        // Fire the network call to download the articles from the feed(s)
        // check the network availability
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            // TODO: 10/10/16 url to be added
            new ArticlesFetchCommand().execute("http://rss.nytimes.com/services/xml/rss/nyt/Americas.xml");
        }else {
            //display error
        }
        // and at the same time load the Articles from Local DB


    }

    @Override
    android.support.v4.app.Fragment getFragmentForActivity() {
        return new MainPagerFragment();
    }

    public enum NewsListType {
        UNREAD("Unread"),
        ALL("All"),
        SAVED("Saved"),
        RECOMMEND("Recom.");
        String title = "";
        NewsListType(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }
    }
}
