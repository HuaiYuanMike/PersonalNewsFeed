package com.example.mikehhsu.personalnewsfeed.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mikehhsu.personalnewsfeed.R;
import com.example.mikehhsu.personalnewsfeed.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by mikehhsu on 7/31/16.
 */
public class MainPagerFragment extends BaseFragment{

    ArrayList<BaseNewsListFragment> newsListFragments = new ArrayList<>();
    ArrayList<Button> listBtns = new ArrayList<>();

    ViewPager viewPager;
    NewsListPagerAdapter newsListPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
        {
            newsListPagerAdapter = new NewsListPagerAdapter(((FragmentActivity)getActivity()).getSupportFragmentManager());
            for(MainActivity.NewsListType listType : MainActivity.NewsListType.values()){
                newsListFragments.add(BaseNewsListFragment.getInstance(listType));
            }
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
