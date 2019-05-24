package com.liquor.adaptermanager.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Liquor on 2019/1/3
 */
public class RecyclerHelper {

    public static final int ISLIST = 10000;//list显示
    public static final int ISGRID = 20000;//grid显示

    public static final int VERTICAL = OrientationHelper.VERTICAL;//纵向
    public static final int HORIZONTAL = OrientationHelper.HORIZONTAL;//横向

    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recycleAdapter;
    private LinearLayoutManager layoutManager;
    private int state = ISLIST;//list还是grid显示
    private int orientation = VERTICAL;//横向还是纵向显示
    private RecyclerSpace recyclerSpace;//分割线帮助类

    /**
     * 构造函数
     */
    private RecyclerHelper(Builder builder) {
        this.context = builder.context;
        this.recyclerView = builder.recyclerView;
        this.recycleAdapter = builder.recycleAdapter;
        this.layoutManager = builder.layoutManager;
        this.state = builder.state;
        this.orientation = builder.orientation;
        this.recyclerSpace = builder.recyclerSpace;
        initView();
    }

    /**
     * build、想展示recycleView必须调用此方法
     */
    private void initView() {
        if (layoutManager == null && state == ISLIST) {
            layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(orientation);
            recyclerView.addItemDecoration(recyclerSpace);
        } else if (layoutManager == null && state == ISGRID) {
            layoutManager = new GridLayoutManager(context, recyclerSpace.getSpanCount());
            recyclerView.addItemDecoration(recyclerSpace);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    public static final class Builder {

        private Context context;
        private RecyclerView recyclerView;
        private RecyclerView.Adapter recycleAdapter;
        private LinearLayoutManager layoutManager;
        private int state;
        private int orientation;
        private RecyclerSpace recyclerSpace;

        public Builder() {
        }

        public Builder context(Context val) {
            context = val;
            return this;
        }

        public Builder recyclerView(RecyclerView val) {
            recyclerView = val;
            return this;
        }

        public Builder recycleAdapter(RecyclerView.Adapter val) {
            recycleAdapter = val;
            return this;
        }

        public Builder layoutManager(LinearLayoutManager val) {
            layoutManager = val;
            return this;
        }

        public Builder state(int val) {
            state = val;
            return this;
        }

        public Builder orientation(int val) {
            orientation = val;
            return this;
        }

        public Builder recyclerSpace(RecyclerSpace val) {
            recyclerSpace = val;
            return this;
        }

        public RecyclerHelper build() {
            return new RecyclerHelper(this);
        }
    }
}