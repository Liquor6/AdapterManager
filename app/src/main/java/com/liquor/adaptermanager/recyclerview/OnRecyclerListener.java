package com.liquor.adaptermanager.recyclerview;

public interface OnRecyclerListener<T> {
    int itemViewLayout();

    boolean itemViewType(T t);

    void itemViewConvert(RecyclerViewHolder holder, T t, int position);
}