package com.example.mikehhsu.personalnewsfeed.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.mikehhsu.personalnewsfeed.R;

/**
 * Created by mikehhsu on 6/25/16.
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, getFragmentForActivity(), null); // no tag for now
        fragmentTransaction.addToBackStack(null); // null for now
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    abstract int getLayoutResID();
    abstract Fragment getFragmentForActivity();

}
