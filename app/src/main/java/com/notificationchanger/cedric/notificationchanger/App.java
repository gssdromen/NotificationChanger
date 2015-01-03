package com.notificationchanger.cedric.notificationchanger;

import android.app.Application;
import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


public class App extends Application {

    public static final boolean DEBUG = true;
    public static final String LOG_TAG = "CedricWu";
    private static App sINSTANCE;
    private Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        sINSTANCE = this;
        mHandler = new Handler();
    }

    public static App getInstance() {
        return sINSTANCE;
    }

    // //////util method
    public static void showShortToast(Object msg) {
        showToast(msg, Toast.LENGTH_SHORT);

    }

    public static void showLongToast(Object msg) {
        showToast(msg, Toast.LENGTH_LONG);
    }

    public static void showToast(final Object msg, final int duration) {
        if (msg == null) {
            return;
        }
        // 保证在 UiThread 中执行 Toast
        sINSTANCE.mHandler.post(new Runnable() {

            @Override
            public void run() {
                Object tempMsg = null;
                if (msg instanceof Integer) {
                    try {
                        tempMsg = sINSTANCE.getResources().getString((Integer) msg);
                    } catch (NotFoundException e) {
                    }
                } else {
                    tempMsg = msg;
                }
                Toast.makeText(sINSTANCE, String.valueOf(tempMsg), duration).show();
            }
        });
    }

    public static void debug(String tag, Object msg) {
        if (DEBUG)
            Log.d(TextUtils.isEmpty(tag) ? LOG_TAG : tag, String.valueOf(msg));
    }

    public static void debug(Object msg) {
        debug(null, msg);
    }

    public static void error(Throwable e, String... tag) {
        if (DEBUG)
            Log.e(tag != null && tag.length > 0 ? tag[0] : LOG_TAG, "", e);
    }

}
