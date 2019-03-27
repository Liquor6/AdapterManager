package com.liquor.recyclerviewlibrary;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Liquor on 2019/1/3
 */
public class RecyclerViewHelper {

    private Context context;
    private RecyclerView recyclerView;

    public RecyclerViewHelper(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    public void setListViewForVertical(RecyclerView.Adapter recycleAdapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    public void setListViewForHorizontal(RecyclerView.Adapter recycleAdapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    public void setGridView(int spanCount, RecyclerView.Adapter recycleAdapter) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    public void setSpaceList() {
        recyclerView.addItemDecoration(new RecyclerSpaceList());
    }

    public void setSpaceList(int leftSpace, int rightSpace, int bottomSpace, int topSpace) {
        recyclerView.addItemDecoration(new RecyclerSpaceList(leftSpace, rightSpace, bottomSpace, topSpace));
    }

    public void setSpaceGrid() {
        recyclerView.addItemDecoration(new RecyclerSpaceGrid());
    }

    public void setSpaceGrid(int spanCount, int spacing, boolean includeEdge) {
        recyclerView.addItemDecoration(new RecyclerSpaceGrid(spanCount, spacing, includeEdge));
    }
}