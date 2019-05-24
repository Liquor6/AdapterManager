package com.liquor.adaptermanager.viewpager;

/**
 * @Title:
 * @Package:
 * @Description: ${TODO}
 * @author: liuyunfeng
 * @date: 2019/5/22
 */
public interface OnCommonPagerListener<T> {
    int itemViewLayout();

    boolean itemViewType(T t);

    void itemViewConvert(CommonPagerViewHolder holder, T t, int position);
}