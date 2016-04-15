package com.startxlabs.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arcfix.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by deep on 15/04/16.
 */
public class LatestNewsActivity extends BaseActivity {
    @Bind(R.id.card_feed_row)
    CardView cardFeedRow;
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


    @Bind(R.id.card_login_view)
    CardView cardLoginView;


    @Bind(R.id.lnr_dismiss_login)
    LinearLayout lnrDismissLogin;

    @Bind(R.id.lnr_login)
    LinearLayout lnrLogin;


    @Bind(R.id.card_update_view)
    CardView cardUpdateView;



    @Bind(R.id.lnr_dismiss_update)
    LinearLayout lnrDismissUpdate;

    @Bind(R.id.lnr_yes_update)
    LinearLayout yesUpdateLnr;

    @Bind(R.id.img_view_more)
    ImageView mImgDetails;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_news);
        ButterKnife.bind(this);
        //TODO: update view with actual data
        cardLoginView.setVisibility(View.GONE);
        cardUpdateView.setVisibility(View.GONE);
        imgIconVideo.setVisibility(View.GONE);
        Picasso.with(this).load(R.mipmap.dummy).into(imgBig);
    }

    @OnClick({R.id.img_view_more,R.id.lnr_favorite,R.id.lnr_share})
    void onViewClick(View view){
        switch (view.getId()){
            case R.id.img_view_more:
                finish();
                break;
        }
    }
}
