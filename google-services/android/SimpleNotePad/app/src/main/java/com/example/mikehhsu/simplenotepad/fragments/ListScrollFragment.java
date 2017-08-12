package com.example.mikehhsu.simplenotepad.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mikehhsu.simplenotepad.R;
import com.example.mikehhsu.simplenotepad.adapters.ListNotesAdapter;

/**
 * Created by mikehhsu on 8/5/17.
 */
public class ListScrollFragment extends Fragment {

    RecyclerView.Adapter adapter;

    public static ListScrollFragment newInstance(){
        Bundle bundle = new Bundle();
        //put arguments;

        ListScrollFragment fragment = new ListScrollFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_scroll, container, false);
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        }.execute();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ListNotesAdapter();
        recyclerView.setAdapter(adapter);
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }
}
