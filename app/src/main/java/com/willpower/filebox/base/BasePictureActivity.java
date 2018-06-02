package com.willpower.filebox.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.willpower.filebox.R;
import com.willpower.filebox.bean.FileBean;
import com.willpower.filebox.tool.ImageLoader;
import com.willpower.filebox.widget.imageview.PhotoView;

import java.util.ArrayList;

/**
 * Created by 冀毅 on 2018/6/2.
 */

public class BasePictureActivity extends BaseActivity{

    PhotoView photoView;

    ArrayList<FileBean> pictures;

    @NonNull
    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_picture;
    }
    @Override
    protected void onActivityCreate(@Nullable Bundle savedInstanceState) {
        photoView = findViewById(R.id.base_photo_view);
        initIntent();
        loadImageByIndex(0);
    }

    private void initIntent(){
        pictures = (ArrayList<FileBean>) getIntent().getSerializableExtra("picture");
    }

    public void onDelete(View v){};
    public void onDetail(View v){};

    private void loadImageByIndex(int index)throws IndexOutOfBoundsException,NullPointerException{
        photoView.enable();
        photoView.setScaleType(ImageView.ScaleType.CENTER);
        ImageLoader.getLoader().loadDeviceImage(pictures.get(index).path,photoView);
    }
}
