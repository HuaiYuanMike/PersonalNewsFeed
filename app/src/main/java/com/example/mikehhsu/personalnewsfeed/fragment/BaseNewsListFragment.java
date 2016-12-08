package com.example.mikehhsu.personalnewsfeed.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.Loader;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.util.Log;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mikehhsu.personalnewsfeed.R;
import com.example.mikehhsu.personalnewsfeed.activity.MainActivity;
import com.example.mikehhsu.personalnewsfeed.db.Article;
import com.example.mikehhsu.personalnewsfeed.loeaders.ArticlesLoader;
import com.example.mikehhsu.personalnewsfeed.network.ImageUrlFetchCommand;

import java.util.ArrayList;

/**
 * Created by mikehhsu on 8/2/16.
 */
public class BaseNewsListFragment extends BaseFragment {

    private static final String KEY_LIST_TYPE = "KEY_LIST_TYPE";
    private ArrayList<Article> articles = new ArrayList<>();

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
        // and at the same time load the Articles from Local DB
        getLoaderManager().initLoader(ArticlesLoader.ARTICLES_LOADER_ID, null,
                new android.support.v4.app.LoaderManager.LoaderCallbacks<ArrayList<Article>>() {
                    @Override
                    public Loader<ArrayList<Article>> onCreateLoader(int id, Bundle args) {
                        return new ArticlesLoader(getContext(), newsListType);
                    }

                    @Override
                    public void onLoadFinished(Loader<ArrayList<Article>> loader, ArrayList<Article> data) {
                        articles = data;
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onLoaderReset(Loader<ArrayList<Article>> loader) {

                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();
        ((TextView)getView().findViewById(R.id.title_temp)).setText(newsListType.getTitle());
    }

    //region contextual menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.news_list_item_floating, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d("mikelog", "item clicled in floating menu: " + item.getTitle());
        return super.onContextItemSelected(item);
    }

    //endregion
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

            Integer i = new Integer(5);
            GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(getContext(), new CardItemSimpleGestureListener());

            public ViewHolder(View itemView) {
                super(itemView);
                titleView = (TextView) itemView.findViewById(R.id.item_news_title);
                headerImage = (ImageView) itemView.findViewById(R.id.item_news_header_img);
                descView = (TextView) itemView.findViewById(R.id.item_news_desc);
//                itemView.setOnClickListener(this);
//                itemView.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        gestureDetectorCompat.onTouchEvent(event);
//                        return true;
//                    }
//                });
                    itemView.setOnCreateContextMenuListener(BaseNewsListFragment.this);
            }



            @Override
            public void onClick(View v) {
                Log.d(getClass().toString(), "article item clicked!");
                if (detailUrl.length() > 0) {
                    getFragmentManager()
                            .beginTransaction()
                            .add(R.id.fragment_container, NewsDetailFragment.getInstance(detailUrl, false), null)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
            }

            class CardItemSimpleGestureListener extends GestureDetector.SimpleOnGestureListener {
                @Override
                public void onLongPress(MotionEvent e) {
                    Log.d("mikelog", "Long Press!");
                    super.onLongPress(e);
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    Log.d("mikelog", "Fling!");
                    return super.onFling(e1, e2, velocityX, velocityY);
                }

                @Override
                public boolean onDown(MotionEvent e) {
                    return super.onDown(e);
                }
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflate and create new viewholder
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_news_card, parent, false);
            //set up margin, padding ...etc here
            int margin = (int) getResources().getDimension(R.dimen.padding_btn_border);
            ((RecyclerView.LayoutParams) view.getLayoutParams()).setMargins(margin, margin, margin, margin);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //update data of each item to the viewholder
            Article article = articles.get(position);
            holder.titleView.setText(article.getTitle());
            holder.descView.setText(article.getDesc());
            holder.detailUrl = articles.get(position).getUrl();
            holder.guild = articles.get(position).getGuid();
            new ImageUrlFetchCommand(getContext(), holder.headerImage).execute(article.getImg());
        }

        @Override
        public int getItemCount() {
            return articles.size();
        }
    }
    //endregion

    public NewsListRecyclerAdapter getAdapter() {
        return adapter;
    }

    public MainActivity.NewsListType getNewsListType() {
        return newsListType;
    }
}
