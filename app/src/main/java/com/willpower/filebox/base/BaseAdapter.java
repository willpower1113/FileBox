package com.willpower.filebox.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 冀毅 on 2018/6/1.
 */

public abstract class BaseAdapter<V extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<V> {
    protected Context context;

    protected ArrayList<T> mData;

    private boolean scrolling;

    public ArrayList<T> getData() {
        return mData;
    }

    public BaseAdapter(Context context, ArrayList<T> mData) {
        this.context = context;
        this.mData = mData;
    }

    public BaseAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<T> list) {
        if (mData != null) {
            mData.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void setNewData(ArrayList<T> list) {
        if (mData != null) {
            mData = list;
        }
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    scrolling = true;
                    Glide.with(context).pauseRequests();
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (scrolling == true) {
                        Glide.with(context).resumeRequests();
                    }
                    scrolling = false;
                }
            }
        });
    }

    OnItemChildClickListener listener;

    public void setListener(OnItemChildClickListener listener) {
        this.listener = listener;
    }

    protected OnItemChildClickListener getListener() {
        return listener;
    }

    public static abstract class OnItemChildClickListener{
        public void onChildClick(BaseAdapter adapter, View view, int position){
        }
        public void onChildLongClick(BaseAdapter adapter, View view, int position){}
    }
}
