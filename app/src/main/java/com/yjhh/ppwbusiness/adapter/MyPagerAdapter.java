package com.yjhh.ppwbusiness.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.yjhh.ppwbusiness.base.BaseFragment;

import java.lang.reflect.Array;
import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {

    public String[] mTitles;

    List<BaseFragment> mFragments;

    public MyPagerAdapter(FragmentManager fm, List<BaseFragment> mFragments, String[] mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
