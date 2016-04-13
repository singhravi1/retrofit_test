package com.startxlabs.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arcfix.R;

/**
 * Created by deep on 13/04/16.
 */
public class SplashInitialLoadingActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_initial);
        if(savedInstanceState==null){

        }
    }

}
