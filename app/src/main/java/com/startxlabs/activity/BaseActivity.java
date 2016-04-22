package com.startxlabs.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.arcfix.R;
import com.startxlabs.fragment.AppBrowserFragment;
import com.startxlabs.fragment.FragmentTabsHome;

import butterknife.Bind;

/**
 * Created by deep on 31/03/16.
 */
public class BaseActivity extends AppCompatActivity {

    public FloatingActionButton fabInitiateChat;


    public  FloatingActionButton mFabSendInquiry;
    public void replaceFragment(String fName, String tag, String backstaktag, Bundle data) {

        Fragment fragment = Fragment.instantiate(this, fName, data);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment, tag);
        transaction.addToBackStack(backstaktag);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        onBackPress();
    }

    public void onBackPress() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            if(getSupportFragmentManager().findFragmentById(R.id.content_main)!=null
                    &&getSupportFragmentManager().findFragmentById(R.id.content_main) instanceof FragmentTabsHome){
                manageFloatingButton(true,false);
            }else if(getSupportFragmentManager().findFragmentById(R.id.content_main)!=null&&getSupportFragmentManager().findFragmentById(R.id.content_main) instanceof AppBrowserFragment){
                manageFloatingButton(false,false);
            }else{
                manageFloatingButton(false,true);
            }
        } else {
            finish();
        }

    }
protected  void manageFloatingButton(boolean doVisibleChat,boolean bothHide){
    if(fabInitiateChat!=null){
        fabInitiateChat.setVisibility(doVisibleChat? View.VISIBLE:View.GONE);
    }
    if(mFabSendInquiry!=null){
        mFabSendInquiry.setVisibility(doVisibleChat? View.GONE:View.VISIBLE);
    }
    if(bothHide&&mFabSendInquiry!=null&&fabInitiateChat!=null){
        mFabSendInquiry.setVisibility(View.GONE);
        fabInitiateChat.setVisibility(View.GONE);
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
