package com.example.mikehhsu.personalnewsfeed.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.mikehhsu.personalnewsfeed.R;

/**
 * Created by mikehhsu on 11/23/16.
 */
public class NewsDetailFragment extends BaseFragment {
    @Override
    int getFragmentLayout() {
        return R.layout.fragment_news_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 11/23/16 loader to load the detailed article
    }
}
