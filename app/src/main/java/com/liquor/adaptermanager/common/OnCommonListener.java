package com.liquor.adaptermanager.common;

/**
 * @Title:
 * @Package:
 * @Description: ${TODO}
 * @author: liuyunfeng
 * @date: 2019/5/24
 */
public interface OnCommonListener<T> {
    int itemViewLayout();

    boolean itemViewType(T t);

    void itemViewConvert(CommonViewHolder holder, T t, int position);
}