package com.liquor.adaptermanager.viewpager;

import android.support.v4.util.SparseArrayCompat;

/**
 * @Title:
 * @Package:
 * @Description: ${TODO}
 * @author: liuyunfeng
 * @date: 2019/5/23
 */
public class CommonPagerDelegate<T> {

    private SparseArrayCompat<OnCommonPagerListener<T>> delegates = new SparseArrayCompat<>();

    /**
     * 添加一个布局类型
     *
     * @param delegate
     * @return
     */
    public CommonPagerDelegate<T> addItemViewType(OnCommonPagerListener<T> delegate) {
        //如果传递进来的对象不是空就将它添加进集合内
        int delegateSize = delegates.size();
        if (delegate != null) {
            delegates.put(delegateSize, delegate);
            return this;
        }
        //This object cannot be empty.
        throw new IllegalArgumentException("This object cannot be empty. ");
    }

    /**
     * 移除一个布局类型
     *
     * @param delegate
     * @return
     */
    public CommonPagerDelegate<T> removeItemViewType(OnCommonPagerListener<T> delegate) {
        //在集合内寻找传递进来的对象如果找到就移除它
        int indexToRemove = delegates.indexOfValue(delegate);
        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
            return this;
        }
        //This object does not exist.
        throw new IllegalArgumentException("This object does not exist. ");
    }

    /**
     * 获取当前条目的OnCommonPagerListener
     *
     * @param item
     * @return
     */
    public OnCommonPagerListener getItemViewListener(T item) {
        int viewType = getItemViewType(item);
        return delegates.get(viewType);
    }

    /**
     * 获取当前条目的视图界面
     *
     * @param item
     * @return
     */
    public int getItemViewLayout(T item) {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            OnCommonPagerListener<T> delegate = delegates.valueAt(i);
            if (delegate.itemViewType(item)) {
                return delegate.itemViewLayout();
            }
        }
        //Collection is empty.
        throw new IllegalArgumentException("Collection is empty. ");
    }

    /**
     * 获取当前条目的视图标识
     *
     * @param item
     * @return
     */
    public int getItemViewType(T item) {
        if (delegates.size() > 0) {
            int delegatesCount = delegates.size();
            for (int i = 0; i < delegatesCount; i++) {
                OnCommonPagerListener<T> delegate = delegates.valueAt(i);
                if (delegate.itemViewType(item)) {
                    return delegates.keyAt(i);
                }
            }
        }
        //Collection is empty.
        throw new IllegalArgumentException("Collection is empty. ");
    }

    /**
     * 映射当前条目
     *
     * @param holder
     * @param item
     * @param position
     */
    public void convert(CommonPagerViewHolder holder, T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            OnCommonPagerListener<T> delegate = delegates.valueAt(i);
            if (delegate.itemViewType(item)) {
                delegate.itemViewConvert(holder, item, position);
                return;
            }
        }
        //Collection is empty.
        throw new IllegalArgumentException("Collection is empty. ");
    }

    public int getCount() {
        return delegates.size();
    }
}