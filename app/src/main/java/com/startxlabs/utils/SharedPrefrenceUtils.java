package com.startxlabs.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

/**
 * Created by deep on 13/04/16.
 */
public class SharedPrefrenceUtils {
    private Context context;
    private static SharedPrefrenceUtils instance;
    public static final String KEY_IS_LOGGED_IN="logged_in";

    public static final String KEY_USER_ID="user_id";

    public static final String KEY_COMPANY_ID="company_id";
    private SharedPrefrenceUtils(Context context){
        this.context=context;
    }

    public static SharedPrefrenceUtils getInstance(Context context){
        if(instance==null){
            instance=new SharedPrefrenceUtils(context);
        }
        instance.context=context.getApplicationContext();
        return instance;
    }
    public String getStringSharedPref(String key){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key,"");
    }
    public boolean getBooleanSharedPref(String key){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key,false);
    }
    public int getIntSharedPref(String key){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key,0);
    }

    public long getLongSharedPref(String key){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key,0l);
    }


    public void setStringSharedPref(String key,String value){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(key,value).commit();

    }

    public void setBooleanSharedPref(String key,boolean value){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean(key,value).commit();

    }


    public void setLongSharedPref(String key,long value){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putLong(key,value).commit();

    }


    public void setIntSharedPref(String key,int value){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt(key,value).commit();
    }

}
