package com.startxlabs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arcfix.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by deep on 14/04/16.
 */
public class IntroductionFragment extends Fragment {
    @Bind(R.id.img_introduction)
    ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_introduction, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = getArguments().getInt("position");
        //TODO: based on pos set image
        if (position < 5) {
            Picasso.with(getActivity()).load(R.mipmap.dummy).into(mImageView);
        } else {
            mImageView.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }
}
