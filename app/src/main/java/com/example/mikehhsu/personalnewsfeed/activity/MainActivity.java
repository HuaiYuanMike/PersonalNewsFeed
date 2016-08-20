package com.example.mikehhsu.personalnewsfeed.activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mikehhsu.personalnewsfeed.R;
import com.example.mikehhsu.personalnewsfeed.fragment.MainPagerFragment;

public class MainActivity extends BaseActivity {

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
