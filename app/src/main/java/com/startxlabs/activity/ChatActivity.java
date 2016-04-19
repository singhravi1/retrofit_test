package com.startxlabs.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.arcfix.R;
import com.startxlabs.fragment.ChatFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by deep on 31/03/16.
 */
public class ChatActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {
            replaceFragment(ChatFragment.class.getName(), ChatFragment.class.getName(), null, null);
        }
    }
}
