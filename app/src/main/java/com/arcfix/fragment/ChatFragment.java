package com.arcfix.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.arcfix.R;
import com.arcfix.activity.BaseActivity;
import com.arcfix.adapter.ChatAdapter;

import butterknife.Bind;

/**
 * Created by deep on 30/03/16.
 */
public class ChatFragment extends BaseListFragment {
ChatAdapter mAdapter;
    @Bind(R.id.edt_chat_message)
    EditText mEdtChatMessage;

    @Bind(R.id.img_attach_file)
    ImageButton mImgAttach;
    @Bind(R.id.img_send)
    ImageButton mImgSend;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
           View feedView = inflater.inflate(R.layout.chat_fragment, container, false);

            return feedView;

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
