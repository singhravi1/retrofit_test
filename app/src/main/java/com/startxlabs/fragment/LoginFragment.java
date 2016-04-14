package com.startxlabs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arcfix.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by deep on 13/04/16.
 */
public class LoginFragment extends Fragment {
    @Bind(R.id.btn_sign_in)
    Button mBtnSignin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login,container,false);
        ButterKnife.bind(v);
        return v;
    }
}
