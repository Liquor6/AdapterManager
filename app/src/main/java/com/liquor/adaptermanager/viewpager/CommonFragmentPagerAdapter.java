package com.liquor.adaptermanager.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Liquor on 2018/10/15
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> titleList;
    private ArrayList<Fragment> fragmentList;

    public CommonFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<String> titleList, ArrayList<Fragment> fragmentList) {
        super(fragmentManager);
        this.titleList = titleList;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }
}