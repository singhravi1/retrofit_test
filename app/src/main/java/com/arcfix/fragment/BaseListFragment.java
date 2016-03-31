package com.arcfix.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arcfix.R;
import com.arcfix.adapter.FeedListAdapter;
import com.arcfix.helper.FeedAdapterCallback;
import com.arcfix.helper.OnStartDragListener;
import com.arcfix.helper.SimpleItemTouchHelperCallback;
import com.arcfix.rest_api.APIClient;
import com.arcfix.rest_api.data_model.Basic;
import com.arcfix.rest_api.data_model.Item;
import com.arcfix.rest_api.data_model.MainResponse;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by deep on 21/03/16.
 */
public class BaseListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.feed_list)
   public RecyclerView mListView;
    @Bind(R.id.progress_bar)
    public ProgressBar mProgressBar;
    @Bind(R.id.swiperefresh)
    public SwipeRefreshLayout mSwipeRefreshLayout;

    protected int visibleItemCount, totalItemCount, firstVisibleItem, previousTotal = 0, visibleThreshold = 1, current_page = 0;

    protected boolean loading=true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View feedView = inflater.inflate(R.layout.fragment_feeds, container, false);

        return feedView;
    }

    protected void setViewData(){

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(layoutManager);
        mListView.setItemAnimator(new FadeInAnimator());
        mListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        < (firstVisibleItem + visibleThreshold)) {
                    // End has been reached

                    // Do something
                    current_page++;

                    getData(false);
//
                    loading = true;
                }
            }
        });
        setViewData();
    }

    protected void getData(boolean showProgressBar) {
        //TODO:  check if internet is available
        if (showProgressBar) {
            mProgressBar.setVisibility(View.VISIBLE);
        }

    }

    protected void setAdapter() {


    }





    @Override
    public void onRefresh() {
        loading = true;
        getData(false);
    }

    public void setRefresing(boolean refresh) {
        loading = refresh;
        mSwipeRefreshLayout.setRefreshing(refresh);
    }
}



