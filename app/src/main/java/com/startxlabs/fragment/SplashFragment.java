package com.startxlabs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arcfix.R;
import com.startxlabs.activity.BaseActivity;
import com.startxlabs.activity.MainActivity;
import com.startxlabs.utils.SharedPrefrenceUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by deep on 13/04/16.
 */
public class SplashFragment extends Fragment {
    @Bind(R.id.img_splash_logo)
    ImageView mImgCompanyLogo;

    @Bind(R.id.img_splash_full_bg)
    ImageView mImgFullBg;

    @Bind(R.id.txt_company_name)
    TextView mTxtCompanyName;

    @Bind(R.id.lnr_loader_container)
    LinearLayout mLnrContainer;
    private Handler mHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler=new Handler();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_splash,container,false);
        ButterKnife.bind(v);
        return  v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
if(SharedPrefrenceUtils.getInstance(getActivity()).getBooleanSharedPref(SharedPrefrenceUtils.KEY_IS_LOGGED_IN)){
    startActivity(new Intent(getActivity(), MainActivity.class));
}else{
    ((BaseActivity)getActivity()).getSupportFragmentManager().popBackStack();
    ((BaseActivity)getActivity()).replaceFragment(LoginFragment.class.getName(),LoginFragment.class.getName(),null,null);
}
            }
        },2000);
    }
}
