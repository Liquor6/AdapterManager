package com.liquor.adaptermanager;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Liquor on 2018/9/13 09:51
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;

    private RecyclerViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
    }

    /**
     * 拿到一个ViewHolder对象
     */
    public static RecyclerViewHolder getViewHolder(View itemView) {
        return new RecyclerViewHolder(itemView);
    }

    /**
     * 通过控件的Id获取对应的控件，如果没有则直接到布局里面找,并加入到views中
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取view数组
     *
     * @return
     */
    public SparseArray<View> getViews() {
        return this.views;
    }

    /**
     * 获取布局的view对象
     *
     * @return
     */
    public View getConvertView() {
        return itemView;
    }

    /**
     * 设置textview字符串显示
     *
     * @param viewId
     * @param text
     * @return
     */
    public RecyclerViewHolder setTextView(int viewId, String text) {
        TextView view = this.getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 设置textview字符串颜色
     *
     * @param viewId
     * @param color
     * @return
     */
    public RecyclerViewHolder setTextColor(int viewId, String color) {
        TextView view = this.getView(viewId);
        view.setTextColor(Color.parseColor(color));
        return this;
    }

    /**
     * 设置textview字符串大小
     *
     * @param viewId
     * @param size
     * @return
     */
    public RecyclerViewHolder setTextSize(int viewId, int size) {
        TextView view = this.getView(viewId);
        view.setTextSize(size);
        return this;
    }

    /**
     * 设置button字符串显示
     *
     * @param viewId
     * @param text
     * @return
     */
    public RecyclerViewHolder setButton(int viewId, String text) {
        Button view = this.getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 设置图片显示
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public RecyclerViewHolder setImageViewResource(int viewId, int drawableId) {
        ImageView view = this.getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    /**
     * 设置图片显示
     *
     * @param viewId
     * @param bitmap
     * @return
     */
    public RecyclerViewHolder setImageViewBitmap(int viewId, Bitmap bitmap) {
        ImageView view = this.getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置view的背景
     *
     * @param viewId
     * @param background
     */
    public void setBackground(int viewId, Drawable background) {
        View view = this.getView(viewId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(background);
        } else {
            view.setBackgroundDrawable(background);
        }
    }

    /**
     * 设置view的背景
     *
     * @param viewId
     * @param resId
     */
    public void setBackground(int viewId, int resId) {
        View view = this.getView(viewId);
        view.setBackgroundResource(resId);
    }

    /**
     * 设置view显示隐藏
     *
     * @param viewId
     * @param visibility
     */
    public void setVisibility(int viewId, int visibility) {
        View view = this.getView(viewId);
        view.setVisibility(visibility);
    }
}