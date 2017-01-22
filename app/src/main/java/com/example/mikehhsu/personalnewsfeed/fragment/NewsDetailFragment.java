package com.example.mikehhsu.personalnewsfeed.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.example.mikehhsu.personalnewsfeed.R;
import com.example.mikehhsu.personalnewsfeed.network.ArticleDetailFetchCommand;

/**
 * Created by mikehhsu on 11/23/16.
 */
public class NewsDetailFragment extends BaseFragment {

    public static final String TEXT_URL = "TEXT_URL";
    public static final String BOOL_SAVED = "BOOL_SAVED";
    String url = null;

    // TODO: 12/2/16 Should do different action base where the detailed article is opened - saved or all article
    // or based on the type of the article -> no way to know that while instantiating the fragment

    public static NewsDetailFragment getInstance(String url, boolean isSaved){
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_URL, url);
        bundle.putBoolean(BOOL_SAVED, isSaved);

        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        newsDetailFragment.setArguments(bundle);
        return newsDetailFragment;
    }

    @Override
    int getFragmentLayout() {
        return R.layout.fragment_news_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().getBoolean(BOOL_SAVED)) {
            // TODO: 11/23/16 loader to load the detailed article
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.url = getArguments().getString(TEXT_URL);
        if(savedInstanceState == null)
        {
            new ArticleDetailFetchCommand((RelativeLayout)getView().findViewById(R.id.f_news_detail_rl_text)).execute(this.url, "1234");
        }
    }

    @Override
    public boolean addToBackStack() {
        return false;
    }
}
