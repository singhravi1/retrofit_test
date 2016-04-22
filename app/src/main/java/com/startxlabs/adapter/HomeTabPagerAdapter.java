package com.startxlabs.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.arcfix.R;
import com.startxlabs.fragment.FeedsFragment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by deep on 30/03/16.
 */
public class HomeTabPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[];
    private Context context;
private HashMap<Integer,Fragment> fragments;
    public HomeTabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        fragments=new HashMap<>();
        this.context = context;
        tabTitles = context.getResources().getStringArray(R.array.tab_title);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (!fragments.containsKey(position))
          fragments.put(position,FeedsFragment.instantiate(context, FeedsFragment.class.getName()));

        return fragments.get(position);

         }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}