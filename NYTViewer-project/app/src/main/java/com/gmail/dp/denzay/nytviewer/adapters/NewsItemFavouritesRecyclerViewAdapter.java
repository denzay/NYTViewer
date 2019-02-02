package com.gmail.dp.denzay.nytviewer.adapters;

import android.os.AsyncTask;

import com.gmail.dp.denzay.nytviewer.AsyncDBImageDownloader;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;
import com.gmail.dp.denzay.nytviewer.views.OnListFragmentInteractionListener;

import java.util.List;

public class NewsItemFavouritesRecyclerViewAdapter extends NewsItemRecyclerViewAdapter {

    public NewsItemFavouritesRecyclerViewAdapter(List<NewsItem> items, OnListFragmentInteractionListener listener) {
        super(items, listener);
    }
    @Override
    protected void doAsyncDownloadImage(ViewHolder holder) {
        new AsyncDBImageDownloader(holder.mImageView.getContext(), holder.mCallback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, String.valueOf(holder.mItem.id));
    }

    public NewsItem getItem(int aIndex) {
        return mValues.get(aIndex);
    }

    public void removeItem(int aIndex) {
        mValues.remove(aIndex);
    }
}
