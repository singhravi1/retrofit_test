package com.startxlabs.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.arcfix.R;

/**
 * Created by deep on 31/03/16.
 */
public class BaseActivity extends AppCompatActivity {


   public void replaceFragment(String fName,String tag,String backstaktag,Bundle data){

        Fragment fragment=Fragment.instantiate(this,fName,data);
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main,fragment,tag);
        transaction.addToBackStack(backstaktag);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        onBackPress();
    }

    public  void onBackPress(){
        if(getSupportFragmentManager().getBackStackEntryCount()>1){
            getSupportFragmentManager().popBackStack();
        }else{
            finish();
        }

    }

    public void disableUserTouch(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    public void enableUserTouch(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
