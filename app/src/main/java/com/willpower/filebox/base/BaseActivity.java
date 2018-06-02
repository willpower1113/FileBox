package com.willpower.filebox.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by 冀毅 on 2018/6/1.
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    protected Context context;
    protected Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        context = this;
        activity = this;
        onActivityCreate(savedInstanceState);
    }

    @NonNull
    protected abstract int getLayoutId();

    protected abstract void onActivityCreate(@Nullable Bundle savedInstanceState);

}
