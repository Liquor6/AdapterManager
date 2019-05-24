package com.liquor.adaptermanager.recyclerview;

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
 */
public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context mContext;
    private List<T> mList;
    private RecyclerDelegate<T> mRecyclerDelegate;

    public RecyclerAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
        mRecyclerDelegate = new RecyclerDelegate<>();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mRecyclerDelegate.getCount() > 0) {
            return mRecyclerDelegate.getItemViewType(mList.get(position));
        } else {
            return super.getItemViewType(position);
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        OnRecyclerListener onRecyclerListener = mRecyclerDelegate.getItemViewListener(viewType);
        View itemView = LayoutInflater.from(mContext).inflate(onRecyclerListener.itemViewLayout(), viewGroup, false);
        return RecyclerViewHolder.getViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        mRecyclerDelegate.convert(holder, mList.get(position), holder.getAdapterPosition());
    }

    public void addItemView(OnRecyclerListener<T> onRecyclerListener) {
        mRecyclerDelegate.addItemViewType(onRecyclerListener);
    }

    public void alterItemView(OnRecyclerListener<T> olditemViewListenerOn, OnRecyclerListener<T> newitemViewListenerOn) {
        mRecyclerDelegate.removeItemViewType(olditemViewListenerOn);
        mRecyclerDelegate.addItemViewType(newitemViewListenerOn);
    }

    public void deleteItemView(OnRecyclerListener<T> onRecyclerListener) {
        mRecyclerDelegate.removeItemViewType(onRecyclerListener);
    }

    public void setData(List<T> list) {
        mList = list;
        this.notifyDataSetChanged();
    }

    public void add(T t) {
        mList.add(t);
        this.notifyDataSetChanged();
    }

    public void add(List<T> list) {
        mList.addAll(list);
        this.notifyDataSetChanged();
    }

    public void add(int size, T t) {
        mList.add(size, t);
        this.notifyItemInserted(size);
    }

    public void add(int size, List<T> list) {
        mList.addAll(size, list);
        this.notifyItemRangeInserted(size, size + list.size());
    }

    public void delete(T t) {
        mList.remove(t);
        this.notifyDataSetChanged();
    }

    public void delete(List<T> list) {
        mList.removeAll(list);
        this.notifyDataSetChanged();
    }

    public void delete(int size) {
        mList.remove(size);
        this.notifyItemRemoved(size);
    }

    public void delete(int startSize, int endSize) {
        for (int i = startSize; i < endSize; i++) {
            mList.remove(i);
        }
        this.notifyItemRangeRemoved(startSize, endSize);
    }

    public void refresh() {
        this.notifyDataSetChanged();
    }

    public void clear() {
        mList.clear();
        this.notifyDataSetChanged();
    }
}