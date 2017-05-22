package com.example.mymvp2.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * com.example.mymvp2.view.fragment
 * 徐世辉  1503A
 * 类作用：本类---
 * 思路：
 * 1.
 * 2.
 * 3.
 * 2017/5/17 21:21
 */

public class HomeFragmentVPFragmentAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> listfragment;
    ArrayList<String> listtitle;

    public HomeFragmentVPFragmentAdapter(FragmentManager fm, ArrayList<Fragment> listfragment, ArrayList<String> listtitle) {
        super(fm);
        this.listfragment = listfragment;
        this.listtitle = listtitle;
    }

    @Override
    public Fragment getItem(int position) {
        return listfragment.get(position);
    }

    @Override
    public int getCount() {
        return listfragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listtitle.get(position);
    }

}
