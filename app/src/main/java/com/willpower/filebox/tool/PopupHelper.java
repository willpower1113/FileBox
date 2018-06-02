package com.willpower.filebox.tool;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.willpower.filebox.R;
import com.willpower.filebox.bean.FileBean;
import com.willpower.filebox.widget.imageview.PhotoView;
import com.willpower.touch.image.AppImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 冀毅 on 2018/6/2.
 */

public class PopupHelper {
    /**
     * 查看大图
     *
     * @param cxt
     * @param parent
     */
    public static void showPicture(Context cxt, List<FileBean> mData, int position, View parent) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.activity_base_picture, null);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView detailText = view.findViewById(R.id.detailText);
        AppImageView delete = view.findViewById(R.id.image_delete);
        AppImageView detail = view.findViewById(R.id.image_detail);
        ViewPager viewPager = view.findViewById(R.id.photo_view_pager);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mData.size();
            }
            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                PhotoView view = new PhotoView(cxt);
                view.enable();
                view.setOnClickListener(view1 -> popupWindow.dismiss());
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                ImageLoader.getLoader().loadDeviceImage(mData.get(position).path, view);
                container.addView(view);
                return view;
            }
            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
        });
        viewPager.setCurrentItem(position);
        delete.setOnClickListener(v -> {
            boolean isSucceed = FileHelper.deleteFile(mData.get(viewPager.getCurrentItem()).path);
            if (isSucceed){
                Toast.makeText(cxt,"删除成功！",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(cxt,"删除失败！",Toast.LENGTH_SHORT).show();
            }
        });//删除文件
        detail.setOnClickListener(v -> {
            detailText.setVisibility(detailText.getVisibility() == View.VISIBLE?View.GONE:View.VISIBLE);
            FileBean fileBean = mData.get(viewPager.getCurrentItem());
            detailText.setText(String.format("文件名称：%s\n文件大小：%s\n文件路径：%s\n最后修改日期：%s"
                    ,fileBean.fileName,fileBean.fileSize,fileBean.path,new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss").format(new Date(fileBean.lastModifyTime))));
        });
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.popupWindow_scale_style);
        popupWindow.setBackgroundDrawable(new ColorDrawable(cxt.getResources().getColor(R.color.colorPrimary)));
        popupWindow.setOutsideTouchable(false);
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
    }
}
