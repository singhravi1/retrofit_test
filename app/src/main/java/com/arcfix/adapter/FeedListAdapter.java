package com.arcfix.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.arcfix.rest_api.data_model.MainResponse;

import butterknife.ButterKnife;

/**
 * Created by deep on 21/03/16.
 */
public class FeedListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

      private MainResponse data;
    private View.OnClickListener onClick;
    private Context context;
    public FeedListAdapter(Context context,View.OnClickListener onClick,MainResponse data){
        this.context=context;
        this.onClick=onClick;
        this.data=data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data!=null?data.getItems().size():0;
    }

    public class RowHolder extends RecyclerView.ViewHolder{

        public RowHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
