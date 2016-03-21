package com.arcfix.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arcfix.R;
import com.arcfix.rest_api.data_model.MainResponse;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
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
        View view= LayoutInflater.from(context).inflate(R.layout.row_feed_list,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Picasso.with(context).load(R.mipmap.dummy).into(((RowHolder)holder).imgBig);
        ((RowHolder)holder).imgIconVideo.setVisibility(View.VISIBLE);
        ((RowHolder)holder).txtHeading.setTextColor(context.getResources().getColor(R.color.white));
    }

    @Override
    public int getItemCount() {
        return data!=null?data.getItems().size():7;
    }

    public class RowHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.image_company_icon)
        ImageView imgCompanyLogo;
        @Bind(R.id.img_item_big_video)
        ImageView imgBig;

        @Bind(R.id.img_icon_video)
        ImageView imgIconVideo;

        @Bind(R.id.img_favorite)
        ImageView imgFavIcon;

        @Bind(R.id.txt_heading)
        TextView txtHeading;

        @Bind(R.id.txt_description)
        TextView txtDesc;

        @Bind(R.id.lnr_bottom_option)
        LinearLayout lnrBottomOptions;

        @Bind(R.id.lnr_favorite)
        LinearLayout lnrFavorite;

        @Bind(R.id.lnr_share)
        LinearLayout lnrShare;


        public RowHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
