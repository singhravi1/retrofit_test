package com.startxlabs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arcfix.R;
import com.startxlabs.activity.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by deep on 13/04/16.
 */
public class AppIntroViewPagerFragment extends Fragment {

    @Bind(R.id.viewpager_tutorial)
    ViewPager mViewPager;
    @Bind(R.id.indicator_skip_intro)
    CircleIndicator mViewPagerIndicator;

    @Bind(R.id.btn_skip_intro)
    Button btnSkip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_app_intro_viewpager, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager.setAdapter();
    }

    @OnClick({R.id.btn_skip_intro})
    void onViewClick(View v) {
        startActivity(new Intent(getActivity(), MainActivity.class));
    }
}
