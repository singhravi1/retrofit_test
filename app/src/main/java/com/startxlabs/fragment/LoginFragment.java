package com.startxlabs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.arcfix.R;
import com.startxlabs.activity.BaseActivity;
import com.startxlabs.activity.DecoderActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by deep on 13/04/16.
 */
public class LoginFragment extends Fragment {
    @Bind(R.id.btn_sign_in)
    Button mBtnSignin;
    @Bind(R.id.img_scan_qr_code)
    ImageView mImgScanQR;
    private Handler mHandler;
@Bind(R.id.edt_token)
 EditText mEdtToken;
    @Bind(R.id.edt_partner_id)
     EditText mEdtPartnerId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @OnClick({R.id.btn_sign_in, R.id.txt_request_account, R.id.img_scan_qr_code})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in:
//                if(mEd)
                mBtnSignin.setText(getString(R.string.loading_3));
                ((BaseActivity) getActivity()).disableUserTouch();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (getActivity() != null) {
                            ((BaseActivity) getActivity()).enableUserTouch();
                            ((BaseActivity) getActivity()).getSupportFragmentManager().popBackStack();
                            ((BaseActivity) getActivity()).replaceFragment(AppIntroViewPagerFragment.class.getName(), AppIntroViewPagerFragment.class.getName(), null, null);
                        }
                    }
                }, 2000);
                break;
            case R.id.img_scan_qr_code:
                startActivity(new Intent(getActivity(), DecoderActivity.class));
                break;
        }
    }
}
