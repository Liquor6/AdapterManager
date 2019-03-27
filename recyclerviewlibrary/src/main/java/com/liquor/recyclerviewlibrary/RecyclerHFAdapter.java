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
 * Created by Liquor on 2018/11/21
 * 同一数据集合下的带有header和footer布局的适配器
 */
public class RecyclerHFAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    private static final int TYPE_DEFAULT = 1000; //一般布局的标识
    private static final int TYPE_HEADER = 2000; //header的标识
    private static final int TYPE_FOOTER = 3000; //footer的标识

    private Context mContext;//上下文
    private List<T> mData = new ArrayList<>();//数据集合

    private boolean isHeader = false;//有header
    private boolean isFooter = false;//有footer

    public RecyclerHFAdapter(Context context, List<T> mData, OnItemViewListener<T> onItemViewListener) {
        this.mContext = context;
        this.mData = mData;
        this.onItemViewListener = onItemViewListener;
    }

    public void addHeaderView(OnHeaderViewListener onHeaderViewListener) {
        isHeader = true;
        this.onHeaderViewListener = onHeaderViewListener;
    }

    public void addFooterView(OnFooterViewListener onFooterViewListener) {
        isFooter = true;
        this.onFooterViewListener = onFooterViewListener;
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
    public int getItemViewType(int position) {
        int type = TYPE_DEFAULT;
        if (isHeader) {
            if (position == 0) {
                type = TYPE_HEADER;
            }
        }
        if (isFooter) {
            if (position == getItemCount() - 1) {
                type = TYPE_FOOTER;
            }
        }
        return type;
    }

    @Override
    public int getItemCount() {
        int num = mData == null ? 0 : mData.size();
        if (isHeader) {
            num += 1;
        }
        if (isFooter) {
            num += 1;
        }
        return num;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType) {
            case TYPE_HEADER:
                itemView = LayoutInflater.from(mContext).inflate(onHeaderViewListener.itemLayout(), parent, false);
                break;
            case TYPE_DEFAULT:
                itemView = LayoutInflater.from(mContext).inflate(onItemViewListener.itemLayout(), parent, false);
                break;
            case TYPE_FOOTER:
                itemView = LayoutInflater.from(mContext).inflate(onFooterViewListener.itemLayout(), parent, false);
                break;
            default:
                break;
        }
        return RecyclerViewHolder.getViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            onHeaderViewListener.itemView(holder);
        }
        if (getItemViewType(position) == TYPE_DEFAULT) {
            if (mData != null && mData.size() > 0) {
                if (isHeader) {//如果存在header就需要将position的值减1
                    onItemViewListener.itemView(holder, position - 1, mData.get(position - 1));
                } else {//如果不存在header正常返回值
                    onItemViewListener.itemView(holder, position, mData.get(position));
                }
            }
        }
        if (getItemViewType(position) == TYPE_FOOTER) {
            onFooterViewListener.itemView(holder);
        }
    }

    private OnItemViewListener<T> onItemViewListener;

    public interface OnItemViewListener<T> {

        int itemLayout();

        void itemView(RecyclerViewHolder holder, int position, T itemDataList);
    }

    private OnHeaderViewListener onHeaderViewListener;

    public interface OnHeaderViewListener {

        int itemLayout();

        void itemView(RecyclerViewHolder holder);
    }

    private OnFooterViewListener onFooterViewListener;

    public interface OnFooterViewListener {

        int itemLayout();

        void itemView(RecyclerViewHolder holder);
    }
}