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
import android.util.Log;

import com.example.mikehhsu.personalnewsfeed.R;
import com.example.mikehhsu.personalnewsfeed.db.Article;
import com.example.mikehhsu.personalnewsfeed.db.NewsFeedDBHelper;
import com.example.mikehhsu.personalnewsfeed.fragment.BaseFragment;
import com.example.mikehhsu.personalnewsfeed.fragment.MainPagerFragment;
import com.example.mikehhsu.personalnewsfeed.loeaders.ArticlesLoader;
import com.example.mikehhsu.personalnewsfeed.network.ArticlesFetchCommand;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    BaseFragment getFragmentForActivity() {
        return new MainPagerFragment();
    }

    public enum NewsListType {
        UNREAD("Unread"),
        ALL("All"),
        FAVORITE("Favorite"),
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
