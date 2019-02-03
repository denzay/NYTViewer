package com.gmail.dp.denzay.nytviewer.adapters;

import android.os.AsyncTask;

import com.gmail.dp.denzay.nytviewer.AsyncDBImageDownloader;
import com.gmail.dp.denzay.nytviewer.models.NewsContent;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;
import com.gmail.dp.denzay.nytviewer.views.OnListFragmentInteractionListener;

public class NewsItemFavouritesRecyclerViewAdapter extends NewsItemRecyclerViewAdapter {

    public NewsItemFavouritesRecyclerViewAdapter(NewsContent aNewsContent, OnListFragmentInteractionListener aListener) {
        super(aNewsContent, aListener);
    }

    @Override
    protected void doAsyncDownloadImage(ViewHolder holder) {
        new AsyncDBImageDownloader(holder.mImageView.getContext(), holder.mCallback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, String.valueOf(holder.mItem.id));
    }

    public NewsItem getItem(int aIndex) {
        return mData.getItems().get(aIndex);
    }

    public void removeItem(int aIndex) {
        mData.getItems().remove(aIndex);
    }
}
