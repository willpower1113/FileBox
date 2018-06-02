package com.willpower;

import android.app.Application;

import com.willpower.filebox.tool.ImageLoader;

/**
 * Created by 冀毅 on 2018/6/1.
 */

public class MyApplication extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.getLoader().init(this);
    }
}
