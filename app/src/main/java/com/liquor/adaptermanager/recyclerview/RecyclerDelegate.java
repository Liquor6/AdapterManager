package com.liquor.adaptermanager.recyclerview;

import android.support.v4.util.SparseArrayCompat;

public class RecyclerDelegate<T> {

    private SparseArrayCompat<OnRecyclerListener<T>> delegates = new SparseArrayCompat<>();

    /**
     * 添加一个布局类型
     *
     * @param delegate
     * @return
     */
    public RecyclerDelegate<T> addItemViewType(OnRecyclerListener<T> delegate) {
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
    public RecyclerDelegate<T> removeItemViewType(OnRecyclerListener<T> delegate) {
        int indexToRemove = delegates.indexOfValue(delegate);
        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
            return this;
        }
        //This object does not exist.
        throw new IllegalArgumentException("This object does not exist. ");
    }

    /**
     * 获取当前条目的OnRecyclerListener
     *
     * @param viewType
     * @return
     */
    public OnRecyclerListener getItemViewListener(int viewType) {
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
            OnRecyclerListener<T> delegate = delegates.valueAt(i);
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
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            OnRecyclerListener<T> delegate = delegates.valueAt(i);
            if (delegate.itemViewType(item)) {
                return delegates.keyAt(i);
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
    public void convert(RecyclerViewHolder holder, T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            OnRecyclerListener<T> delegate = delegates.valueAt(i);
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