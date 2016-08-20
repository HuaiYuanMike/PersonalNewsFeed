package com.example.mikehhsu.personalnewsfeed.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.mikehhsu.personalnewsfeed.R;
import com.example.mikehhsu.personalnewsfeed.activity.MainActivity;

/**
 * Created by mikehhsu on 8/2/16.
 */
public class BaseNewsListFragment extends BaseFragment {
    private MainActivity.NewsListType newsListType = null;
    private static final String KEY_LIST_TYPE = "KEY_LIST_TYPE";

    @Override
    int getFragmentLayout() {
        return R.layout.fragment_news_list;
    }

    public static BaseNewsListFragment getInstance(MainActivity.NewsListType listType) {

        BaseNewsListFragment fragment = new BaseNewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_LIST_TYPE, listType.name());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newsListType = MainActivity.NewsListType.valueOf(getArguments().getString(KEY_LIST_TYPE, MainActivity.NewsListType.ALL.name()));

        // display temp. title
//        Log.d("mikelog", "external storage path (root of the file): " + getContext().getExternalFilesDir(null));
//        Log.d("mikelog", "external storage path (mode music): " + getContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC));
//        Log.d("mikelog", "external storage path (getFilesDir): " + getContext().getFilesDir());
    }

    @Override
    public void onStart() {
        super.onStart();
        ((TextView)getView().findViewById(R.id.title_temp)).setText(newsListType.getTitle());
    }
}
