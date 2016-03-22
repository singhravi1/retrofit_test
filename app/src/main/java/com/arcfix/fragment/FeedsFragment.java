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

import com.arcfix.R;
import com.arcfix.adapter.FeedListAdapter;
import com.arcfix.helper.OnStartDragListener;
import com.arcfix.helper.SimpleItemTouchHelperCallback;
import com.arcfix.rest_api.APIClient;
import com.arcfix.rest_api.data_model.Item;
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
public class FeedsFragment extends Fragment implements OnStartDragListener, SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.feed_list)
    RecyclerView mListView;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private MainResponse data;
    private FeedListAdapter mAdapter;
    private int lastRemoved = -1;
    private Item mItemRemoved;
    private ItemTouchHelper mItemTouchHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View feedView = inflater.inflate(R.layout.fragment_feeds, container, false);
        ButterKnife.bind(this, feedView);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(layoutManager);
        mListView.setHasFixedSize(true);

        if (data == null) {
            getData(true);
        } else {
            setAdapter();
        }
        return feedView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    void getData(boolean showProgressBar) {
        if (showProgressBar) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
        Call<MainResponse> apiCall = APIClient.getInstance().getRestAdapter().getMainFeeds();
        apiCall.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Response<MainResponse> response, Retrofit retrofit) {
                if (getActivity() == null)
                    return;
                mProgressBar.setVisibility(View.GONE);
                if (response != null && response.body() != null) {
                    data = response.body();
                    setAdapter();
                } else {
                    Snackbar.make(getView(), response.message(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    setAdapter();//set dummy adapter
                }
                setRefresing(false);
            }

            @Override
            public void onFailure(Throwable t) {
                if (getActivity() == null)
                    return;
                Snackbar.make(getView(), t.getMessage(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                mProgressBar.setVisibility(View.GONE);
                setRefresing(false);
            }
        });
    }

    void setAdapter() {
        mListView.setItemAnimator(new FadeInAnimator());
        mAdapter = new FeedListAdapter(getActivity(), onClick, data, this);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mListView);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
        SlideInBottomAnimationAdapter scaleAdapter = new SlideInBottomAnimationAdapter(alphaAdapter);
        mListView.setAdapter(scaleAdapter);

    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    /**
     * This method will be called when a list item is removed
     *
     * @param position The position of the item within data set
     */
    public void onItemRemoved(int position) {
        Snackbar snackbar = Snackbar.make(
                getView(),
                R.string.app_name,
                Snackbar.LENGTH_LONG);

        snackbar.setAction(R.string.undo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemUndoActionClicked();
            }
        });
        snackbar.setActionTextColor(ContextCompat.getColor(getActivity(), R.color.grey));
        snackbar.show();
    }


    public void onItemPinned(int position) {

    }


    public void onItemClicked(int position) {
        mAdapter.notifyItemChanged(position);
    }

    private void onItemRemovedActionClicked(int position, Item object) {

        lastRemoved = position;
        mItemRemoved = object;
    }


    private void onItemUndoActionClicked() {
//        set last removed object in variable
        data.getItems().add(lastRemoved, mItemRemoved);
        mAdapter.notifyItemInserted(lastRemoved);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    public void onRefresh() {
        getData(false);
    }

    public void setRefresing(boolean refresh) {
        mSwipeRefreshLayout.setRefreshing(refresh);

    }
}



