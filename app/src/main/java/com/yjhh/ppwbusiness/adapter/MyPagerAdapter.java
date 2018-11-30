package com.yjhh.ppwbusiness.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.yjhh.ppwbusiness.base.BaseFragment;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;

    private List<BaseFragment> mFragments;

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
