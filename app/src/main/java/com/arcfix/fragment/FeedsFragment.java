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
    private int visibleItemCount, totalItemCount, firstVisibleItem, previousTotal = 0, visibleThreshold = 1, current_page = 0;

    private boolean loading=true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View feedView = inflater.inflate(R.layout.fragment_feeds, container, false);
        ButterKnife.bind(this, feedView);
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
        //TODO:  check if internet is available
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

                    if(data==null){
                        data = response.body();
                    Item itemLogin = new Item();
                    Basic basicLogin = new Basic();
                    basicLogin.setTitle("Login");
                    itemLogin.setBasic(basicLogin);
                    data.getItems().add(itemLogin);

                    Item item = new Item();
                    Basic basic = new Basic();
                    basic.setTitle("Upgrade");
                    item.setBasic(basic);
                    data.getItems().add(item);

                    }else{
                        data.getItems().addAll(response.body().getItems());
                    }
                    setAdapter();
                } else {
                    Snackbar.make(getView(), response.message(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
//                    setAdapter();//set dummy adapter
                }
                setRefresing(false);
                loading = false;
            }

            @Override
            public void onFailure(Throwable t) {
                if (getActivity() == null)
                    return;
                Snackbar.make(getView(), t.getMessage(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                mProgressBar.setVisibility(View.GONE);
                setRefresing(false);
                loading = false;

            }
        });
    }

    void setAdapter() {
        mProgressBar.setVisibility(View.GONE);
        if(mAdapter==null){
        mAdapter = new FeedListAdapter(getActivity(), onClick, data, this, callback);
        }else{
            mAdapter.setData(data);
            mAdapter.notifyDataSetChanged();
        }
        if(mItemTouchHelper==null){
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);

        mItemTouchHelper.attachToRecyclerView(mListView);

        }
        mListView.setAdapter(mAdapter);
//        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
//        SlideInBottomAnimationAdapter scaleAdapter = new SlideInBottomAnimationAdapter(alphaAdapter);


    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };


    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

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

    FeedAdapterCallback callback = new FeedAdapterCallback() {
        @Override
        public void onLoadMore() {
            current_page++;
            loading = true;
            getData(false);

        }

        @Override
        public void onItemRemove(int position, Item object) {
            loading = true;
            lastRemoved = position;
            mItemRemoved = object;
            Snackbar snackbar = Snackbar.make(
                    getView(),
                    R.string.item_removed,
                    Snackbar.LENGTH_LONG);

            snackbar.setAction(R.string.undo, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUndoAction(lastRemoved, mItemRemoved);
                }
            });
            snackbar.setActionTextColor(ContextCompat.getColor(getActivity(), R.color.grey));
            snackbar.show();
            loading = false;
        }

        @Override
        public void onUndoAction(int position, Item object) {
            data.getItems().add(lastRemoved, mItemRemoved);
            mAdapter.notifyItemInserted(lastRemoved);
        }

        @Override
        public void onDragDrop(int positionOne, int positiontwo) {

        }
    };
}



