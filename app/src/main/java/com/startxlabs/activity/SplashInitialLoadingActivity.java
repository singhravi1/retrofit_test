package com.startxlabs.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arcfix.R;
import com.startxlabs.fragment.SplashFragment;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by deep on 13/04/16.
 */
public class SplashInitialLoadingActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash_initial);
        if (savedInstanceState == null) {
            replaceFragment(SplashFragment.class.getName(), SplashFragment.class.getName(), null, null);
        }
    }

}
