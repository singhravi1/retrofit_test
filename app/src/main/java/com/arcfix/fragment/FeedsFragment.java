package com.arcfix.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arcfix.R;
import com.arcfix.adapter.FeedListAdapter;
import com.arcfix.rest_api.APIClient;
import com.arcfix.rest_api.data_model.MainResponse;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by deep on 21/03/16.
 */
public class FeedsFragment extends Fragment {
    @Bind(R.id.feed_list)
    RecyclerView mListView;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    private MainResponse data;
    private FeedListAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View feedView=inflater.inflate(R.layout.fragment_feeds,container,false);
        ButterKnife.bind(this,feedView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(layoutManager);
        if(data==null){
            getData();
        }else{
            setAdapter();
        }
        return feedView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    void getData(){

        mProgressBar.setVisibility(View.VISIBLE);
        Call<MainResponse> apiCall= APIClient.getInstance().getRestAdapter().getMainFeeds();
        apiCall.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Response<MainResponse> response, Retrofit retrofit) {
                if(getActivity()==null)
                    return;

                Snackbar.make(getView(),response.message(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                mProgressBar.setVisibility(View.GONE);
                if(response!=null&&response.body()!=null){
                    data=response.body();
                    setAdapter();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if(getActivity()==null)
                    return;
                Snackbar.make(getView(),t.getMessage(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }
    void setAdapter(){
        mListView.setItemAnimator(new FadeInAnimator());

        mAdapter = new FeedListAdapter(getActivity(), onClick,data);

        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
        SlideInBottomAnimationAdapter scaleAdapter = new SlideInBottomAnimationAdapter(alphaAdapter);
        mListView.setAdapter(scaleAdapter);
    }
    private View.OnClickListener onClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
