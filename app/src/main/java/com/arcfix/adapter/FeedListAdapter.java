package com.arcfix.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
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
import butterknife.OnClick;

/**
 * Created by deep on 21/03/16.
 */
public class FeedListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private MainResponse data;
    private View.OnClickListener onClick;
    private Context context;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    public FeedListAdapter(Context context, View.OnClickListener onClick, MainResponse data) {
        this.context = context;
        this.onClick = onClick;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {

            View view = LayoutInflater.from(context).inflate(R.layout.row_feed_list, parent, false);
            return new RowHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_layout_footer, parent, false);
            return new FooterHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RowHolder) {
            Picasso.with(context).load(R.mipmap.dummy).into(((RowHolder) holder).imgBig);
            ((RowHolder) holder).imgIconVideo.setVisibility(View.VISIBLE);
            ((RowHolder) holder).txtHeading.setTextColor(context.getResources().getColor(R.color.white));
        } else if (holder instanceof FooterHolder) {

        }
    }


    @Override
    public int getItemCount() {
        return data != null ? data.getItems().size()+1 : 7;
    }

    @Override
    public int getItemViewType(int position) {
        if (data!=null?position < data.getItems().size():position<6) {
            return TYPE_ITEM;
        } else if (data!=null?position == data.getItems().size():position==6) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public class RowHolder extends RecyclerView.ViewHolder {

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

        @OnClick({})
        void onViewClick(View view) {

        }
    }

    public class FooterHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_load_more_view)
        CardView cardViewMore;
        @Bind(R.id.card_login_view)
        CardView cardLoginView;

        @Bind(R.id.card_update_view)
        CardView cardUpdateView;

        @Bind(R.id.lnr_dismiss_login)
        LinearLayout lnrDismissLogin;

        @Bind(R.id.lnr_login)
        LinearLayout lnrLogin;

        @Bind(R.id.lnr_dismiss_update)
        LinearLayout lnrDismissUpdate;

        @Bind(R.id.lnr_yes_update)
        LinearLayout yesUpdateLnr;

        public FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.lnr_login, R.id.lnr_dismiss_update, R.id.lnr_dismiss_login, R.id.lnr_yes_update, R.id.card_load_more_view})
        void onViewClick(View view) {
            switch (view.getId()) {
                case R.id.lnr_dismiss_login:

                    cardLoginView.setVisibility(View.GONE);
                    break;
                case R.id.lnr_login:
                    break;
                case R.id.lnr_yes_update:
                    break;
                case R.id.lnr_dismiss_update:
                    cardUpdateView.setVisibility(View.GONE);
                    break;
                case R.id.card_load_more_view:

                    //TODO: call API
                    cardViewMore.setVisibility(View.GONE);
                    break;

            }
        }
    }
}
