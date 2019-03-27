package com.liquor.adaptermanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liquor on 2019/3/14
 * 不同数据集合下的多种布局适配器
 */
public class RecyclerSAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context mContext;//上下文
    private List<OnItemViewListener> itemList = new ArrayList<>();//回调接口集合

    public RecyclerSAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * 增加一个单个的view
     *
     * @param onItemViewListener
     */
    public void addView(OnItemViewListener onItemViewListener) {
        //添加一个view
        this.itemList.add(onItemViewListener);
    }

    /**
     * 增加一个集合的view
     *
     * @param size
     * @param onItemViewListener
     */
    public void addView(int size, OnItemViewListener onItemViewListener) {
        //添加一个list的view
        for (int i = 0; i < size; i++) {
            this.itemList.add(onItemViewListener);
        }
    }

    /**
     * 更新view，没有动画效果
     */
    public void refresh() {
        this.notifyDataSetChanged();
    }

    /**
     * 移除并更新view，同时具有动画效果
     */
    public void removeView(int position) {
        this.itemList.remove(position);
        this.notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).itemType();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutId = 0;
        for (OnItemViewListener item : itemList) {
            //通过要展示view的viewtype来获取到要展示view的布局id
            if (viewType == item.itemType()) {
                layoutId = item.itemLayout();
                break;
            }
        }
        View itemView = LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false);
        return RecyclerViewHolder.getViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        if (itemList != null && itemList.size() > 0) {//判断集合不为空时
            int number = 0;
            for (int i = 0; i < position; i++) {
                //通过position的运算来计算出回调函数的真正下角标
                if (itemList.get(i).itemType() != itemList.get(position).itemType()) {
                    number += 1;
                }
            }
            itemList.get(position).itemView(holder, position - number);
        }
    }

    public interface OnItemViewListener {

        int itemLayout();

        int itemType();

        void itemView(RecyclerViewHolder view, int position);
    }
}