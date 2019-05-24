package com.liquor.adaptermanager.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liquor on 2018/4/23
 */
public class CommonPagerAdapter<T> extends PagerAdapter {

    private Context mContext;
    private List<T> mList;
    private CommonPagerDelegate<T> mCommonPagerDelegate;

    public CommonPagerAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
        mCommonPagerDelegate = new CommonPagerDelegate<>();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return mList.indexOf(object);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int position) {
        CommonPagerViewHolder viewHolder = CommonPagerViewHolder.getViewHolder(mContext, mCommonPagerDelegate.getItemViewLayout(mList.get(position)), viewGroup);
        mCommonPagerDelegate.convert(viewHolder, mList.get(position), position);
        return viewHolder.getConvertView();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup viewGroup, int position, @NonNull Object object) {
        viewGroup.removeView((View) object);
    }

    public void addItemView(OnCommonPagerListener<T> onCommonPagerListener) {
        mCommonPagerDelegate.addItemViewType(onCommonPagerListener);
    }

    public void alterItemView(OnCommonPagerListener<T> olditemViewListenerOn, OnCommonPagerListener<T> newitemViewListenerOn) {
        mCommonPagerDelegate.removeItemViewType(olditemViewListenerOn);
        mCommonPagerDelegate.addItemViewType(newitemViewListenerOn);
    }

    public void deleteItemView(OnCommonPagerListener<T> onCommonPagerListener) {
        mCommonPagerDelegate.removeItemViewType(onCommonPagerListener);
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