package com.example.mikehhsu.personalnewsfeed.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mikehhsu on 7/3/16.
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), null);
        return view;
    }
    abstract int getFragmentLayout();

    public boolean addToBackStack()
    {
        return true;
    }

}
