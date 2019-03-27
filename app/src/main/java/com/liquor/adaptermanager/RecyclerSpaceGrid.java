package com.liquor.adaptermanager;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Liquor on 2018/10/16
 */
public class RecyclerSpaceGrid extends RecyclerView.ItemDecoration {

    private int spanCount;//列数
    private int spacing;//间隔
    private boolean includeEdge;//是否包含边缘

    public RecyclerSpaceGrid() {
        this.spanCount = 3;
        this.spacing = 10;
        this.includeEdge = true;
    }

    public RecyclerSpaceGrid(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int spacings = Utils_Display.dp2px(parent.getContext(), spacing);
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