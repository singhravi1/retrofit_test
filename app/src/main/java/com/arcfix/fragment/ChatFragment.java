package com.arcfix.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arcfix.R;
import com.arcfix.activity.BaseActivity;
import com.arcfix.adapter.ChatAdapter;

/**
 * Created by deep on 30/03/16.
 */
public class ChatFragment extends BaseListFragment {
ChatAdapter mAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    public void setViewData(){

        mProgressBar.setVisibility(View.GONE);
        setAdapter();
    }

    @Override
    protected void setAdapter() {
        mAdapter=new ChatAdapter(getActivity(),null,null);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_chat_view, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            ((BaseActivity) getActivity()).onBackPress();
        } else if (item.getItemId() == R.id.action_archive) {
        //TODO:
        }
        return super.onOptionsItemSelected(item);


    }
}
