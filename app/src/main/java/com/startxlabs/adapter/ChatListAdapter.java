package com.startxlabs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arcfix.R;
import com.squareup.picasso.Picasso;
import com.startxlabs.rest_api.data_model.MainResponse;
import com.startxlabs.ui_utils.CircleTransform;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by deep on 30/03/16.
 */
public class ChatListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    private MainResponse data;
    private View.OnClickListener onClick;
    private Context context;

    public ChatListAdapter(Context context, View.OnClickListener onClick, MainResponse data) {
        this.context = context;
        this.onClick = onClick;
        this.data = data;
    }

    public void setData(MainResponse data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_chat_list, parent, false);
            return new RowHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RowHolder) {
//                Item item=data.getItems().get(position);
            Picasso.with(context).load(R.mipmap.dummy).transform(new CircleTransform()).into(((RowHolder) holder).imgChatUserLogo);

        }

    }


    @Override
    public int getItemCount() {
        return data != null ? data.getItems().size() : 10;
    }

    @Override
    public int getItemViewType(int position) {

        return TYPE_ITEM;

    }


    public class RowHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.row_chat_list)
        LinearLayout chatListRow;
        @Bind(R.id.img_chat_user)
        ImageView imgChatUserLogo;

        @Bind(R.id.txt_sender_name)
        TextView txtSenderName;

        @Bind(R.id.txt_time)
        TextView txtTime;


        public RowHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.row_chat_list})
        void onViewClick(View view) {
            switch (view.getId()) {

                case R.id.row_chat_list:
                    break;


            }
        }

    }


}
