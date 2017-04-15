package com.example.andy.connectutil.Activity;

import android.app.Application;
import android.content.Context;

/**
 * Created by andy on 2017/4/15.
 */

public class MyApplication extends Application {

    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = getApplicationContext();
    }

    public static Context getContext()
    {
        return instance;
    }

}
