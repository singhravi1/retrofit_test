package com.arcfix.fragment;

import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arcfix.R;
import com.arcfix.adapter.FeedListAdapter;
import com.arcfix.rest_api.APIClient;
import com.arcfix.rest_api.data_model.Item;
import com.arcfix.rest_api.data_model.MainResponse;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;

import java.util.HashMap;

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
    private RecyclerView.Adapter mWrappedAdapter;
    private RecyclerViewDragDropManager mRecyclerViewDragDropManager;
    private RecyclerViewSwipeManager mRecyclerViewSwipeManager;
    private RecyclerViewTouchActionGuardManager mRecyclerViewTouchActionGuardManager;
private int lastRemoved=-1;
    private Item mItemRemoved;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View feedView = inflater.inflate(R.layout.fragment_feeds, container, false);
        ButterKnife.bind(this, feedView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(layoutManager);
        if (data == null) {
            getData();
        } else {
            setAdapter();
        }
        return feedView;
    }

//void setSwipeDrag(){
//
//    // touch guard manager  (this class is required to suppress scrolling while swipe-dismiss animation is running)
//    mRecyclerViewTouchActionGuardManager = new RecyclerViewTouchActionGuardManager();
//    mRecyclerViewTouchActionGuardManager.setInterceptVerticalScrollingWhileAnimationRunning(true);
//    mRecyclerViewTouchActionGuardManager.setEnabled(true);
//
//
//    mRecyclerViewDragDropManager = new RecyclerViewDragDropManager();
//
////    mRecyclerViewDragDropManager.setDraggingItemShadowDrawable(
////            (NinePatchDrawable) ContextCompat.getDrawable(getContext(), R.drawable.material_shadow_z3));
//
//    // swipe manager
//    mRecyclerViewSwipeManager = new RecyclerViewSwipeManager();
//
//    //adapter
//    final MyDraggableSwipeableItemAdapter myItemAdapter = new MyDraggableSwipeableItemAdapter(getDataProvider());
//    myItemAdapter.setEventListener(new MyDraggableSwipeableItemAdapter.EventListener() {
//        @Override
//        public void onItemRemoved(int position) {
//            ((DraggableSwipeableExampleActivity) getActivity()).onItemRemoved(position);
//        }
//
//        @Override
//        public void onItemPinned(int position) {
//            ((DraggableSwipeableExampleActivity) getActivity()).onItemPinned(position);
//        }
//
//        @Override
//        public void onItemViewClicked(View v, boolean pinned) {
//            onItemViewClick(v, pinned);
//        }
//    });
//
//    mAdapter = myItemAdapter;
//
//    mWrappedAdapter = mRecyclerViewDragDropManager.createWrappedAdapter(myItemAdapter);      // wrap for dragging
//    mWrappedAdapter = mRecyclerViewSwipeManager.createWrappedAdapter(mWrappedAdapter);      // wrap for swiping
//
//    final GeneralItemAnimator animator = new SwipeDismissItemAnimator();
//
//    // Change animations are enabled by default since support-v7-recyclerview v22.
//    // Disable the change animation in order to make turning back animation of swiped item works properly.
//    animator.setSupportsChangeAnimations(false);
//
//    mRecyclerView.setLayoutManager(mLayoutManager);
//    mRecyclerView.setAdapter(mWrappedAdapter);  // requires *wrapped* adapter
//    mRecyclerView.setItemAnimator(animator);
//
//    // additional decorations
//    //noinspection StatementWithEmptyBody
//    if (supportsViewElevation()) {
//        // Lollipop or later has native drop shadow feature. ItemShadowDecorator is not required.
//    } else {
//        mRecyclerView.addItemDecoration(new ItemShadowDecorator((NinePatchDrawable) ContextCompat.getDrawable(getContext(), R.drawable.material_shadow_z1)));
//    }
//    mRecyclerView.addItemDecoration(new SimpleListDividerDecorator(ContextCompat.getDrawable(getContext(), R.drawable.list_divider_h), true));
//
//    // NOTE:
//    // The initialization order is very important! This order determines the priority of touch event handling.
//    //
//    // priority: TouchActionGuard > Swipe > DragAndDrop
//    mRecyclerViewTouchActionGuardManager.attachRecyclerView(mRecyclerView);
//    mRecyclerViewSwipeManager.attachRecyclerView(mRecyclerView);
//    mRecyclerViewDragDropManager.attachRecyclerView(mRecyclerView);
//}
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    void getData() {
        mProgressBar.setVisibility(View.VISIBLE);
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
            }

            @Override
            public void onFailure(Throwable t) {
                if (getActivity() == null)
                    return;
                Snackbar.make(getView(), t.getMessage(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    void setAdapter() {
        mListView.setItemAnimator(new FadeInAnimator());
        mAdapter = new FeedListAdapter(getActivity(), onClick, data);
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

    private void onItemRemovedActionClicked(int position,Item object) {

        lastRemoved=position;
        mItemRemoved=object;
    }


    private void onItemUndoActionClicked() {
//        set last removed object in variable
data.getItems().add(lastRemoved,mItemRemoved);
           mAdapter.notifyItemInserted(lastRemoved);
        }
    }



