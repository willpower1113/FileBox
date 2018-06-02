package com.willpower.filebox.ablum;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.willpower.filebox.R;
import com.willpower.filebox.adapter.FolderAdapter;
import com.willpower.filebox.base.BaseActivity;
import com.willpower.filebox.base.BaseAdapter;
import com.willpower.filebox.bean.FileBean;
import com.willpower.filebox.bean.FolderBean;
import com.willpower.filebox.tool.FileHelper;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 冀毅 on 2018/6/1.
 */

public class FolderActivity extends BaseActivity {
    RecyclerView rv;
    FolderAdapter adapter;
    ProgressBar mProgress;

    private int type;

    @NonNull
    @Override
    protected int getLayoutId() {
        return R.layout.activity_album;
    }

    @Override
    protected void onActivityCreate(@Nullable Bundle savedInstanceState) {
        rv = findViewById(R.id.folder_recycler);
        mProgress = findViewById(R.id.folder_progress);
        rv.setLayoutManager(new GridLayoutManager(context, 2));
        inIntent();
    }


    private void inIntent() {
        type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case 0://相册
                initAlbumRecycler();
                break;
            case 1://音频
                initMusicRecycler();
                break;
            case 2://视频
                initVideoRecycler();
                break;
        }
    }

    /**
     * 显示所有相册
     */
    private void initAlbumRecycler() {
        Observable.create((ObservableOnSubscribe<ArrayList<FolderBean<FileBean>>>)
                e -> e.onNext(FileHelper.getHelper().findPictures())).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        folderBeans -> {
                            mProgress.setVisibility(View.GONE);
                            adapter = new FolderAdapter(context, folderBeans);
                            rv.setAdapter(adapter);
                            adapter.setListener(new BaseAdapter.OnItemChildClickListener() {
                                @Override
                                public void onChildClick(BaseAdapter adapter, View view, int position) {
                                    //点击封面
                                    FolderBean<FileBean> folderBean = (FolderBean<FileBean>) adapter.getData().get(position);
                                    startActivity(new Intent(context, AlbumActivity.class).putExtra("picture", folderBean.childs));
                                }

                                @Override
                                public void onChildLongClick(BaseAdapter adapter, View view, int position) {
                                }
                            });
                        },
                        throwable -> Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show());
    }

    /**
     * 显示所有音乐
     */
    private void initMusicRecycler() {
        Observable.create((ObservableOnSubscribe<ArrayList<FolderBean<FileBean>>>)
                e -> e.onNext(FileHelper.getHelper().findMusics())).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        folderBeans -> {
                            mProgress.setVisibility(View.GONE);
                            adapter = new FolderAdapter(context, folderBeans);
                            rv.setAdapter(adapter);
                            adapter.setListener(new BaseAdapter.OnItemChildClickListener() {
                                @Override
                                public void onChildClick(BaseAdapter adapter, View view, int position) {
                                    //点击封面
                                    FolderBean<FileBean> folderBean = (FolderBean<FileBean>) adapter.getData().get(position);
                                    startActivity(new Intent(context, AlbumActivity.class).putExtra("picture", folderBean.childs));
                                }

                                @Override
                                public void onChildLongClick(BaseAdapter adapter, View view, int position) {
                                }
                            });
                        },
                        throwable -> Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show());
    }

    /**
     * 显示所有视频
     */
    private void initVideoRecycler() {
        Observable.create((ObservableOnSubscribe<ArrayList<FolderBean<FileBean>>>)
                e -> e.onNext(FileHelper.getHelper().findVideos())).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        folderBeans -> {
                            mProgress.setVisibility(View.GONE);
                            adapter = new FolderAdapter(context, folderBeans);
                            rv.setAdapter(adapter);
                            adapter.setListener(new BaseAdapter.OnItemChildClickListener() {
                                @Override
                                public void onChildClick(BaseAdapter adapter, View view, int position) {
                                    //点击封面
                                    FolderBean<FileBean> folderBean = (FolderBean<FileBean>) adapter.getData().get(position);
                                    startActivity(new Intent(context, AlbumActivity.class).putExtra("picture", folderBean.childs));
                                }
                                @Override
                                public void onChildLongClick(BaseAdapter adapter, View view, int position) {
                                }
                            });
                        },
                        throwable -> Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show());
    }

}
