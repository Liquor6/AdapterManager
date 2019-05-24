package com.liquor.adaptermanager.recyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Liquor on 2018/10/16
 */
public class RecyclerSpace extends RecyclerView.ItemDecoration {

    private int viewState;

    private int ISLIST = 12345;//list类型
    private int ISGRID = 54321;//grid类型

    //list类型
    private int spacing_left = 0;//左边
    private int spacing_right = 0;//右边
    private int spacing_bottom = 0;//下边
    private int spacing_top = 0;//上边

    //grid类型
    private int spanCount = 0;//列数
    private int spacing = 10;//间隔
    private boolean includeEdge = true;//是否包含边缘

    public RecyclerSpace(int left, int right, int bottom, int top) {
        spacing_left = left;
        spacing_right = right;
        spacing_bottom = bottom;
        spacing_top = top;
        viewState = ISLIST;
    }

    public RecyclerSpace(int count, int space, boolean edge) {
        spanCount = count;
        spacing = space;
        includeEdge = edge;
        viewState = ISGRID;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (viewState == ISLIST) {
            outRect.left = dp2px(parent.getContext(), spacing_left);
            outRect.right = dp2px(parent.getContext(), spacing_right);
            outRect.bottom = dp2px(parent.getContext(), spacing_bottom);
            if (parent.getChildPosition(view) == 0)
                outRect.top = dp2px(parent.getContext(), spacing_top);
        } else if (viewState == ISGRID) {
            int spacings = dp2px(parent.getContext(), spacing);
            //这里是关键，需要根据你有几列来判断
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column
            if (includeEdge) {
                outRect.left = spacings - column * spacings / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacings / spanCount; // (column + 1) * ((1f / spanCount) * spacing)
                if (position < spanCount) { // top edge
                    outRect.top = spacings;
                }
                outRect.bottom = spacings; // item bottom
            } else {
                outRect.left = column * spacings / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacings - (column + 1) * spacings / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacings; // item top
                }
            }
        }
    }

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int getSpacing_left() {
        return spacing_left;
    }

    public void setSpacing_left(int spacing_left) {
        this.spacing_left = spacing_left;
    }

    public int getSpacing_right() {
        return spacing_right;
    }

    public void setSpacing_right(int spacing_right) {
        this.spacing_right = spacing_right;
    }

    public int getSpacing_bottom() {
        return spacing_bottom;
    }

    public void setSpacing_bottom(int spacing_bottom) {
        this.spacing_bottom = spacing_bottom;
    }

    public int getSpacing_top() {
        return spacing_top;
    }

    public void setSpacing_top(int spacing_top) {
        this.spacing_top = spacing_top;
    }

    public int getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    public int getSpacing() {
        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

    public boolean isIncludeEdge() {
        return includeEdge;
    }

    public void setIncludeEdge(boolean includeEdge) {
        this.includeEdge = includeEdge;
    }
}