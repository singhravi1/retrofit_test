package com.arcfix.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arcfix.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by deep on 30/03/16.
 */
public class InquiryFragment extends Fragment {
    @Bind(R.id.spinner_inquiry)
    AppCompatSpinner spinner;
    @Bind(R.id.edt_message_inquiry)
    EditText mEdtMessage;
    @Bind(R.id.edt_subject_inquiry)
    EditText mEdtSubject;

    @Bind(R.id.btn_submit_inquiry)
    Button mBtnSubmit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inquiry_form,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.inquiry));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
    }
    @OnClick({R.id.btn_submit_inquiry})
    void onClickView(View view){
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
