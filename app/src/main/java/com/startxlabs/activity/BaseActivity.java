package com.startxlabs.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.arcfix.R;

/**
 * Created by deep on 31/03/16.
 */
public class BaseActivity extends AppCompatActivity {

    public FloatingActionButton fabInitiateChat;

private Handler mHandler;
    protected boolean isLive;
    public  FloatingActionButton mFabSendInquiry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler=new Handler();
        isLive=true;
    }



    @Override
    protected void onPause() {
        super.onPause();
        if(isFinishing()){
            isLive=false;
        }
    }

    public void disableUserTouch() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void enableUserTouch() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
