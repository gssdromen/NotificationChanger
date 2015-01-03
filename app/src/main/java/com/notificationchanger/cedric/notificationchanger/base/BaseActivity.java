package com.notificationchanger.cedric.notificationchanger.base;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by dromenwu on 14/12/30.
 */
public class BaseActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME);
        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayUseLogoEnabled(false);
//        actionBar.setDisplayShowHomeEnabled(false);
    }
}
