package com.example.mikehhsu.personalnewsfeed.activity;

import android.app.Activity;
import android.app.Fragment;

import com.example.mikehhsu.personalnewsfeed.R;
import com.example.mikehhsu.personalnewsfeed.fragment.LoginFragment;

/**
 * Created by mikehhsu on 6/23/16.
 */
public class LoginActivity extends BaseActivity {
    @Override
    int getLayoutResID() {
        return R.layout.activity_base;
    }

    @Override
    Fragment getFragmentForActivity() {
        return LoginFragment.newInstance();
    }
}
