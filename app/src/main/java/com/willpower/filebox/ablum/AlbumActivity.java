package com.willpower.filebox.ablum;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.willpower.filebox.R;
import com.willpower.filebox.adapter.PictureAdapter;
import com.willpower.filebox.base.BaseActivity;
import com.willpower.filebox.base.BaseAdapter;
import com.willpower.filebox.bean.FileBean;
import com.willpower.filebox.tool.PopupHelper;

import java.util.ArrayList;

/**
 * Created by 冀毅 on 2018/6/2.
 */

public class AlbumActivity extends BaseActivity {
    RecyclerView rv;
    ProgressBar mProgress;

    PictureAdapter adapter;

    ArrayList<FileBean> pictures;

    @NonNull
    @Override
    protected int getLayoutId() {
        return R.layout.activity_album;
    }

    @Override
    protected void onActivityCreate(@Nullable Bundle savedInstanceState) {
        rv = findViewById(R.id.folder_recycler);
        mProgress = findViewById(R.id.folder_progress);
        inIntent();
        initRecycler();
    }

    private void inIntent() {
        pictures = (ArrayList<FileBean>) getIntent().getSerializableExtra("picture");
    }

    private void initRecycler() {
        mProgress.setVisibility(View.GONE);
        rv.setLayoutManager(new GridLayoutManager(context, 2));
        adapter = new PictureAdapter(context, pictures);
        rv.setAdapter(adapter);
        adapter.setListener(new BaseAdapter.OnItemChildClickListener() {
            @Override
            public void onChildClick(BaseAdapter adapter, View view, int position) {
                //点击封面
                PopupHelper.showPicture(context, pictures, position, rv);
            }
            @Override
            public void onChildLongClick(BaseAdapter adapter, View view, int position) {
            }
        });
    }


}
