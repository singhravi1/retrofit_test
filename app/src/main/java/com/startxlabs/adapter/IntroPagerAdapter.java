package com.startxlabs.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.startxlabs.fragment.IntroductionFragment;

/**
 * Created by deep on 14/04/16.
 */
public class IntroPagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private int itemCount;
    public IntroPagerAdapter(FragmentManager fm,Context context, int itemCount){
        super(fm);
        this.context=context;
        this.itemCount=itemCount;
    }
    @Override
    public Fragment getItem(int position) {
        Bundle b=new Bundle();
        b.putInt("position",position);
        return IntroductionFragment.instantiate(context,IntroductionFragment.class.getName(),b);
    }

    @Override
    public int getCount() {
        return itemCount;
    }
}
