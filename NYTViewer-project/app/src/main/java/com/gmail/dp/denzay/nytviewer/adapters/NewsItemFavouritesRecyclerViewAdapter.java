package com.gmail.dp.denzay.nytviewer.adapters;

import android.databinding.BindingAdapter;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.gmail.dp.denzay.nytviewer.AsyncDBImageDownloader;
import com.gmail.dp.denzay.nytviewer.models.NewsContent;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;
import com.gmail.dp.denzay.nytviewer.views.OnListFragmentInteractionListener;

public class NewsItemFavouritesRecyclerViewAdapter extends NewsItemRecyclerViewAdapter {

    public NewsItemFavouritesRecyclerViewAdapter(NewsContent aNewsContent, OnListFragmentInteractionListener aListener) {
        super(aNewsContent, aListener);
    }

    @BindingAdapter({"id", "url", "onDownloadComplete"})
    @Override
    public void doAsyncDownloadImage(ImageView view, long id, String url, OnImageDownloadCompleteListener aListener) {
        new AsyncDBImageDownloader(view.getContext(), aListener).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, String.valueOf(id));
    }

    public NewsItem getItem(int aIndex) {
        return mData.getItems().get(aIndex);
    }

    public void removeItem(int aIndex) {
        mData.getItems().remove(aIndex);
    }
}
