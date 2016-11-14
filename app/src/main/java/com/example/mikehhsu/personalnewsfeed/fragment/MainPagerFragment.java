package com.example.mikehhsu.personalnewsfeed.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mikehhsu.personalnewsfeed.R;
import com.example.mikehhsu.personalnewsfeed.activity.MainActivity;
import com.example.mikehhsu.personalnewsfeed.db.Article;
import com.example.mikehhsu.personalnewsfeed.loeaders.ArticlesLoader;
import com.example.mikehhsu.personalnewsfeed.network.ArticlesFetchCommand;

import java.util.ArrayList;

/**
 * Created by mikehhsu on 7/31/16.
 */
public class MainPagerFragment extends BaseFragment{

    ArrayList<BaseNewsListFragment> newsListFragments = new ArrayList<>();
    ArrayList<Button> listBtns = new ArrayList<>();

    ViewPager viewPager;
    NewsListPagerAdapter newsListPagerAdapter;

    //Fragments for each pages
    BaseNewsListFragment allNewsFragment = BaseNewsListFragment.getInstance(MainActivity.NewsListType.ALL);
    BaseNewsListFragment unreadNewsFragment = BaseNewsListFragment.getInstance(MainActivity.NewsListType.UNREAD);
    BaseNewsListFragment savedNewsFragment = BaseNewsListFragment.getInstance(MainActivity.NewsListType.SAVED);
    BaseNewsListFragment recommendedNewsFragment = BaseNewsListFragment.getInstance(MainActivity.NewsListType.RECOMMEND);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
        {
            newsListPagerAdapter = new NewsListPagerAdapter(((FragmentActivity)getActivity()).getSupportFragmentManager());
            newsListFragments.add(allNewsFragment);
            newsListFragments.add(unreadNewsFragment);
            newsListFragments.add(savedNewsFragment);
            newsListFragments.add(recommendedNewsFragment);
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final View root = getView();

        //viewpager
        viewPager = (ViewPager)root.findViewById(R.id.main_pager);
        viewPager.setAdapter(newsListPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0 ; i < listBtns.size() ; i ++){
                    if(i == position){
                        //todo: this is temp action
                        listBtns.get(i).setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    }else
                    {
                        //todo: this is temp action
                        listBtns.get(i).setTextColor(getResources().getColor(android.R.color.white));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(MainActivity.NewsListType.ALL.ordinal());


        //region buttons
        listBtns.add((Button)root.findViewById(R.id.btn_unread_list));
        listBtns.add((Button)root.findViewById(R.id.btn_all_list));
        listBtns.add((Button)root.findViewById(R.id.btn_saved_list));
        listBtns.add((Button)root.findViewById(R.id.btn_recom_list));
        listBtns.get(MainActivity.NewsListType.ALL.ordinal()).setTextColor(getResources().getColor(android.R.color.holo_green_dark));

        listBtns.get(MainActivity.NewsListType.UNREAD.ordinal()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(MainActivity.NewsListType.UNREAD.ordinal());
            }
        });
        listBtns.get(MainActivity.NewsListType.ALL.ordinal()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(MainActivity.NewsListType.ALL.ordinal());
            }
        });
        listBtns.get(MainActivity.NewsListType.SAVED.ordinal()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(MainActivity.NewsListType.SAVED.ordinal());
            }
        });
        listBtns.get(MainActivity.NewsListType.RECOMMEND.ordinal()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(MainActivity.NewsListType.RECOMMEND.ordinal());
            }
        });
        //endregion

        // Fire the network call to download the articles from the feed(s)
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            new ArticlesFetchCommand().execute();
        }else {
            Log.e(this.getClass().toString(), "Network connection not available!");
        }

        // and at the same time load the Articles from Local DB
        getLoaderManager().initLoader(ArticlesLoader.ARTICLES_LOADER_ID, null,
                new android.support.v4.app.LoaderManager.LoaderCallbacks<ArrayList<Article>>() {
                    @Override
                    public Loader<ArrayList<Article>> onCreateLoader(int id, Bundle args) {
                        return new ArticlesLoader(getContext());
                    }

                    @Override
                    public void onLoadFinished(Loader<ArrayList<Article>> loader, ArrayList<Article> data) {
                        BaseNewsListFragment.setRawNewsArticles(data);
                        allNewsFragment.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onLoaderReset(Loader<ArrayList<Article>> loader) {

                    }
                });

    }

    @Override
    int getFragmentLayout() {
        return R.layout.fragment_main_pager;
    }

    class NewsListPagerAdapter extends FragmentPagerAdapter
    {

        public NewsListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return newsListFragments.get(position);
        }

        @Override
        public int getCount() {
            return newsListFragments.size();
        }
    }




}
