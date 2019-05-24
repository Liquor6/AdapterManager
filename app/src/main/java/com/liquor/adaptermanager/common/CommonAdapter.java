package com.liquor.adaptermanager.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liquor on 2018/4/26
 */
public class CommonAdapter<T> extends BaseAdapter {

    private Context mContext;
    private List<T> mList;
    private CommonDelegate<T> mCommonDelegate;

    public CommonAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
        mCommonDelegate = new CommonDelegate<>();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view, mContext, mCommonDelegate.getItemViewLayout(mList.get(position)), viewGroup);
        mCommonDelegate.convert(viewHolder, mList.get(position), position);
        return viewHolder.getConvertView();
    }

    public void addItemView(OnCommonListener<T> onCommonListener) {
        mCommonDelegate.addItemViewType(onCommonListener);
    }

    public void alterItemView(OnCommonListener<T> olditemViewListenerOn, OnCommonListener<T> newitemViewListenerOn) {
        mCommonDelegate.removeItemViewType(olditemViewListenerOn);
        mCommonDelegate.addItemViewType(newitemViewListenerOn);
    }

    public void deleteItemView(OnCommonListener<T> onCommonListener) {
        mCommonDelegate.removeItemViewType(onCommonListener);
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
        this.notifyDataSetChanged();
    }

    public void add(int size, List<T> list) {
        mList.addAll(size, list);
        this.notifyDataSetChanged();
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
        this.notifyDataSetChanged();
    }

    public void delete(int startSize, int endSize) {
        for (int i = startSize; i < endSize; i++) {
            mList.remove(i);
        }
        this.notifyDataSetChanged();
    }

    public void refresh() {
        this.notifyDataSetChanged();
    }

    public void clear() {
        mList.clear();
        this.notifyDataSetChanged();
    }
}