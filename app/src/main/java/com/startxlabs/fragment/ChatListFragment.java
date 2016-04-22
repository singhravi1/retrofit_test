package com.startxlabs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.startxlabs.adapter.ChatListAdapter;
import com.startxlabs.ui_utils.DividerItemDecoration;

/**
 * Created by deep on 30/03/16.
 */
public class ChatListFragment extends BaseChatListFragment {
    int fragmentPos;
    private ChatListAdapter mAdapter;
    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentPos = getArguments().getInt("position");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        mListView.addItemDecoration(itemDecoration);
    }

    @Override
    protected void setViewData() {
        super.setViewData();
        setAdapter(true);
    }

    @Override
    protected void setAdapter(boolean setAdapter) {
        super.setAdapter(setAdapter);
        mProgressBar.setVisibility(View.GONE);
        if(mAdapter==null){
        mAdapter = new ChatListAdapter(getActivity(), onClick, null);
            mListView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
        if(setAdapter){
        mListView.setAdapter(mAdapter);
        }
    }
}

