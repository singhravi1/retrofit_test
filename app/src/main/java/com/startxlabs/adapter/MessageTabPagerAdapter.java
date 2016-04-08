package com.startxlabs.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.arcfix.R;
import com.startxlabs.fragment.ChatListFragment;

/**
 * Created by deep on 30/03/16.
 */
public class MessageTabPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] ;
    private Context context;

    public MessageTabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        tabTitles = context.getResources().getStringArray(R.array.message_tab_title);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle b=new Bundle();
        b.putInt("position",position);
        return ChatListFragment.instantiate(context,ChatListFragment.class.getName(),b);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
