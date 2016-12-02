package com.example.mikehhsu.personalnewsfeed.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mikehhsu.personalnewsfeed.R;
import com.example.mikehhsu.personalnewsfeed.activity.MainActivity;
import com.example.mikehhsu.personalnewsfeed.db.Article;
import com.example.mikehhsu.personalnewsfeed.network.ArticleDetailFetchCommand;
import com.example.mikehhsu.personalnewsfeed.network.ImageUrlFetchCommand;

import java.util.ArrayList;

/**
 * Created by mikehhsu on 8/2/16.
 */
public class BaseNewsListFragment extends BaseFragment {

    private static final String KEY_LIST_TYPE = "KEY_LIST_TYPE";
    private static ArrayList<Article> rawNewsArticles = new ArrayList<>();

    private MainActivity.NewsListType newsListType = null;
    private RecyclerView recyclerView;
    private NewsListRecyclerAdapter adapter;

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
        String name = getArguments().getString(KEY_LIST_TYPE, MainActivity.NewsListType.ALL.name());
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
        recyclerView = (RecyclerView) getView().findViewById(R.id.list_news_recycler_view);
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
    public class NewsListRecyclerAdapter extends RecyclerView.Adapter<NewsListRecyclerAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            //defines the viewholder
            TextView titleView;
            ImageView headerImage;
            TextView descView;
            String detailUrl = "";
            String guild = "";
            public ViewHolder(View itemView) {
                super(itemView);
                titleView = (TextView) itemView.findViewById(R.id.item_news_title);
                headerImage = (ImageView) itemView.findViewById(R.id.item_news_header_img);
                descView = (TextView) itemView.findViewById(R.id.item_news_desc);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Log.d(getClass().toString(), "article item clicked!");
                if(detailUrl.length() > 0) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(getId(), NewsDetailFragment.getInstance(detailUrl,false), null)
                            .commit();
                }
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflate and create new viewholder
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_news_card,parent,false);
            //set up margin, padding ...etc here
            int margin = (int)getResources().getDimension(R.dimen.padding_btn_border);
            ((RecyclerView.LayoutParams)view.getLayoutParams()).setMargins(margin, margin, margin, margin);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //update data of each item to the viewholder
            Article article = rawNewsArticles.get(position);
            holder.titleView.setText(article.getTitle());
            holder.descView.setText(article.getDesc());
            holder.detailUrl = rawNewsArticles.get(position).getUrl();
            holder.guild = rawNewsArticles.get(position).getGuid();
            new ImageUrlFetchCommand(getContext(), holder.headerImage).execute(article.getImg());
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

    public MainActivity.NewsListType getNewsListType() {
        return newsListType;
    }
}
