package com.example.mikehhsu.personalnewsfeed.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mikehhsu.personalnewsfeed.fragment.BaseFragment;
import com.example.mikehhsu.personalnewsfeed.fragment.NewsDetailFragment;

/**
 * Created by mikehhsu on 1/7/17.
 */
public class NewsDetailActivity extends BaseActivity {

    static final String KEY_URL = "key_url";
    static final String KEY_IS_SAVED = "is_saved";

    public static void StartMe(Context context, String url, boolean isSaved)
    {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(KEY_URL, url);
        intent.putExtra(KEY_IS_SAVED, isSaved);

        context.startActivity(intent);
    }




    @Override
    BaseFragment getFragmentForActivity() {
        return NewsDetailFragment.getInstance(getIntent().getStringExtra(KEY_URL),
                getIntent().getBooleanExtra(KEY_IS_SAVED, false));
    }
}
