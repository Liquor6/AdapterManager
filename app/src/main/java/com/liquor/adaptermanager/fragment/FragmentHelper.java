package com.liquor.adaptermanager.fragment;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * Created by Liquor on 2018/9/15
 */
public class FragmentHelper {

    private FragmentManager mFragmentManager;
    private int mContainerViewID;

    /**
     * @param fragmentManager 管理类
     * @param containerViewId 布局容器
     */
    public FragmentHelper(@Nullable FragmentManager fragmentManager, @IdRes int containerViewId) {
        this.mFragmentManager = fragmentManager;
        this.mContainerViewID = containerViewId;
    }

    public List<Fragment> getFragmentDatas() {
        return mFragmentManager.getFragments();
    }

    /**
     * 添加Fragment
     *
     * @param fragment 需要添加的Fragment
     */
    public void addFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(mContainerViewID, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 切换保存数据Fragment
     *
     * @param fragment 进行切换的Fragment
     */
    public void switchFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();
        for (Fragment cFragment : fragments) {
            //将集合中所有的fragment遍历出来然后全部隐藏以便后面展示
            fragmentTransaction.hide(cFragment);
        }
        //进行判断framgnet是否在List里面存在  如果不存在就添加
        if (!fragments.contains(fragment)) {
            fragmentTransaction.add(mContainerViewID, fragment);
        } else {
            //如果存在就展示出来
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
    }

    /**
     * 进项Fragment切换但每次都要刷新数据
     *
     * @param framgent
     */
    public void switchFragment_RefreshData(Fragment framgent) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(mContainerViewID, framgent);
        fragmentTransaction.commit();
    }
}