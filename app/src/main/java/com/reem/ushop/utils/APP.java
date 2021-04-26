package com.reem.ushop.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

public class APP extends Application {

    public static APP mInstance;
    public static final String TAG = APP.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    public static synchronized APP getInstance() {
        return mInstance;
    }

}
