package com.liquor.recyclerviewlibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liquor on 2018/9/13
 * 同一数据集合下的单个布局适配器
 */
public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context mContext;//上下文
    private List<T> mData;//数据集合

    public RecyclerAdapter(Context context, List<T> mData, OnItemViewListener<T> onItemViewListener) {
        this.mContext = context;
        this.mData = mData;
        this.onItemViewListener = onItemViewListener;
    }

    /**
     * 加载数据,没有动画效果
     */
    public void setData(List<T> mData) {
        this.mData = new ArrayList<>();
        this.mData.addAll(mData);
        this.notifyDataSetChanged();
    }

    /**
     * 添加并更新数据，同时具有动画效果
     */
    public void addData(int position, T data) {
        this.mData.add(position, data);
        this.notifyItemInserted(position);//更新数据集，注意如果用adapter.notifyDataSetChanged()将没有动画效果
    }

    /**
     * 更新数据，没有动画效果
     */
    public void refresh() {
        this.notifyDataSetChanged();
    }

    /**
     * 移除并更新数据，同时具有动画效果
     */
    public void removeData(int position) {
        this.mData.remove(position);
        this.notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(onItemViewListener.itemLayout(), viewGroup, false);
        return RecyclerViewHolder.getViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        if (mData != null && mData.size() > 0) {
            onItemViewListener.itemView(holder, position, mData.get(position));
        }
    }

    private OnItemViewListener<T> onItemViewListener;

    public interface OnItemViewListener<T> {

        int itemLayout();

        void itemView(RecyclerViewHolder holder, int position, T itemData);
    }
}