package com.willpower.filebox.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.willpower.filebox.R;
import com.willpower.filebox.base.BaseAdapter;
import com.willpower.filebox.bean.FolderBean;
import com.willpower.filebox.tool.ImageLoader;

import java.util.ArrayList;

/**
 * Created by 冀毅 on 2018/6/1.
 */

public class FolderAdapter<E> extends BaseAdapter<FolderAdapter.ViewHolder, FolderBean<E>> {

    Context context;

    public FolderAdapter(Context context, ArrayList<FolderBean<E>> mData) {
        super(context, mData);
        this.mData = mData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_album, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FolderBean<E> bean = mData.get(position);
        ImageLoader.getLoader().loadDeviceImage(bean.coverPath, holder.imageView);
        holder.textView.setText(bean.fileName);
        holder.imageView.setOnClickListener(v -> getListener().onChildClick(FolderAdapter.this, v, position));
        holder.imageView.setOnLongClickListener(v -> {
            getListener().onChildClick(FolderAdapter.this, v, position);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_album_recycler);
            textView = itemView.findViewById(R.id.tv_album_recycler);
        }
    }
}
