package com.notificationchanger.cedric.notificationchanger;

import android.app.Application;

/**
 * Created by dromenwu on 14/12/28.
 */
public class App extends Application {
    private static App sInstance;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        sInstance = this;
    }

    public static App getInstance() {
        return sInstance;
    }
}
