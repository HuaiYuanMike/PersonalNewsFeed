package com.example.mikehhsu.personalnewsfeed.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mikehhsu.personalnewsfeed.R;

/**
 * Created by mikehhsu on 6/25/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, getFragmentForActivity(), null); // no tag for now
        fragmentTransaction.addToBackStack(null); // null for now
        fragmentTransaction.commit();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_action);
        setSupportActionBar(myToolbar);

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
