package com.example.mikehhsu.personalnewsfeed.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.mikehhsu.personalnewsfeed.R;

/**
 * Created by mikehhsu on 6/25/16.
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, getFragmentForActivity(), null); // no tag for now
        fragmentTransaction.addToBackStack(null); // null for now
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    int getLayout() {
        return R.layout.activity_base;
    }

    abstract android.support.v4.app.Fragment getFragmentForActivity();

}
