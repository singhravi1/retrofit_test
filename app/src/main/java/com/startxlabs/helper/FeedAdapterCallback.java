package com.startxlabs.helper;

import com.startxlabs.rest_api.data_model.Item;

/**
 * Created by deep on 22/03/16.
 */
public interface FeedAdapterCallback {
    void onLoadMore();

    void onItemRemove(int position, Item object);

    void onUndoAction(int position, Item object);

    void onDragDrop(int positionOne, int positiontwo);
}
