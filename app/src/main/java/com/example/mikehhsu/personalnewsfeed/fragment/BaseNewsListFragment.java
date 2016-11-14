package com.example.mikehhsu.personalnewsfeed.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mikehhsu.personalnewsfeed.R;
import com.example.mikehhsu.personalnewsfeed.activity.MainActivity;
import com.example.mikehhsu.personalnewsfeed.db.Article;

import java.util.ArrayList;

/**
 * Created by mikehhsu on 8/2/16.
 */
public class BaseNewsListFragment extends BaseFragment {
    private MainActivity.NewsListType newsListType = null;
    private static final String KEY_LIST_TYPE = "KEY_LIST_TYPE";
    private RecyclerView recyclerView = null;
    private NewsListRecyclerAdapter adapter = null;

    private static ArrayList<Article> rawNewsArticles = new ArrayList<>();

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new NewsListRecyclerAdapter();
        recyclerView = (RecyclerView)getView().findViewById(R.id.list_news_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        ((TextView)getView().findViewById(R.id.title_temp)).setText(newsListType.getTitle());
    }


    //region NewsListRecyclerAdapter
    //define the adapter for the recycler view
    public class NewsListRecyclerAdapter extends RecyclerView.Adapter<NewsListRecyclerAdapter.ViewHolder>{

        public class ViewHolder extends RecyclerView.ViewHolder{
            //defines the viewholder
            TextView titleView;
            public ViewHolder(View itemView) {
                super(itemView);
                titleView = (TextView) itemView.findViewById(R.id.item_news_title);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflate and create new viewholder
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_news_card,parent,false);
            //todo: set view's size, margin, padding
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //update data of each item to the viewholder
            holder.titleView.setText(rawNewsArticles.get(position).getTitle());
        }

        @Override
        public int getItemCount() {
            return rawNewsArticles.size();
        }
    }
    //endregion


    public static ArrayList<Article> getRawNewsArticles() {
        return rawNewsArticles;
    }

    public static void setRawNewsArticles(ArrayList<Article> rawNewsArticles) {
        BaseNewsListFragment.rawNewsArticles = rawNewsArticles;
    }

    public NewsListRecyclerAdapter getAdapter() {
        return adapter;
    }
}
