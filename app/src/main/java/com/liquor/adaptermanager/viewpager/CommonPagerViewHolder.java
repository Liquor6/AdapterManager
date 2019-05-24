package com.liquor.adaptermanager.viewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.util.SparseArrayCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @Title:
 * @Package:
 * @Description: ${TODO}
 * @author: liuyunfeng
 * @date: 2019/5/23
 */
public class CommonPagerViewHolder {

    private SparseArrayCompat<View> views;
    private View itemView;

    private CommonPagerViewHolder(Context context, int layoutId, ViewGroup viewGroup) {
        this.views = new SparseArrayCompat<>();
        this.itemView = LayoutInflater.from(context).inflate(layoutId, viewGroup, false);
    }

    /**
     * 拿到一个ViewHolder对象
     */
    public static CommonPagerViewHolder getViewHolder(Context context, int layoutId, ViewGroup viewGroup) {
        return new CommonPagerViewHolder(context, layoutId, viewGroup);
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
    public SparseArrayCompat<View> getViews() {
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
    public CommonPagerViewHolder setTextView(int viewId, String text) {
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
    public CommonPagerViewHolder setTextColor(int viewId, String color) {
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
    public CommonPagerViewHolder setTextSize(int viewId, int size) {
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
    public CommonPagerViewHolder setButton(int viewId, String text) {
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
    public CommonPagerViewHolder setImageViewResource(int viewId, int drawableId) {
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
    public CommonPagerViewHolder setImageViewBitmap(int viewId, Bitmap bitmap) {
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