package com.liquor.recyclerviewlibrary;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Liquor on 2018/10/16
 */
public class RecyclerSpaceList extends RecyclerView.ItemDecoration {

    //定义局部变量
    private int leftSpace;
    private int rightSpace;
    private int bottomSpace;
    private int topSpace;

    public RecyclerSpaceList() {
        this.leftSpace = 10;
        this.rightSpace = 10;
        this.bottomSpace = 10;
        this.topSpace = 10;
    }

    public RecyclerSpaceList(int leftSpace, int rightSpace, int bottomSpace, int topSpace) {
        this.leftSpace = leftSpace;
        this.rightSpace = rightSpace;
        this.bottomSpace = bottomSpace;
        this.topSpace = topSpace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = Utils_Display.dp2px(parent.getContext(), leftSpace);
        outRect.right = Utils_Display.dp2px(parent.getContext(), rightSpace);
        outRect.bottom = Utils_Display.dp2px(parent.getContext(), bottomSpace);
        if (parent.getChildPosition(view) == 0)
            outRect.top = Utils_Display.dp2px(parent.getContext(), topSpace);
    }
}