package com.ford.memoryleak;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class MemoryLeakApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);
    }
}
