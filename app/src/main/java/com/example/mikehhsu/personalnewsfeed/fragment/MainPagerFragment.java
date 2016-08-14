package com.example.mikehhsu.personalnewsfeed.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.mikehhsu.personalnewsfeed.R;

import java.util.ArrayList;

/**
 * Created by mikehhsu on 7/31/16.
 */
public class MainPagerFragment extends BaseFragment{

    ViewPager viewPager;
    ArrayList<BaseNewsListFragment> newsListFragments = new ArrayList<>();
    NewsListPagerAdapter newsListPagerAdapter;
    public MainPagerFragment(){
        super();
    }
//todo: enum for each news list type - UNREAD, ALL, SAVED, and Recommend
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
        {
            newsListPagerAdapter = new NewsListPagerAdapter(((FragmentActivity)getActivity()).getSupportFragmentManager());
            newsListFragments.add(new BaseNewsListFragment());
            newsListFragments.add(new BaseNewsListFragment());
            newsListFragments.add(new BaseNewsListFragment());


        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager = (ViewPager) getView().findViewById(R.id.main_pager);
        viewPager.setAdapter(newsListPagerAdapter);
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
