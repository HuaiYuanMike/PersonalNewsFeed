package com.example.mikehhsu.personalnewsfeed.activity;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;

import com.example.mikehhsu.personalnewsfeed.R;
import com.example.mikehhsu.personalnewsfeed.db.Article;
import com.example.mikehhsu.personalnewsfeed.db.NewsFeedDBHelper;
import com.example.mikehhsu.personalnewsfeed.fragment.MainPagerFragment;
import com.example.mikehhsu.personalnewsfeed.loeaders.ArticlesLoader;

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
        getLoaderManager().initLoader(1234, null,
                    new LoaderManager.LoaderCallbacks<ArrayList<Article>>() {
                    @Override
                    public Loader<ArrayList<Article>> onCreateLoader(int id, Bundle args) {
                        return new ArticlesLoader(MainActivity.this);
                    }

                    @Override
                    public void onLoadFinished(Loader<ArrayList<Article>> loader, ArrayList<Article> data) {

                    }

                        @Override
                    public void onLoaderReset(Loader<ArrayList<Article>> loader) {

                    }
                });
        //endregion
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
