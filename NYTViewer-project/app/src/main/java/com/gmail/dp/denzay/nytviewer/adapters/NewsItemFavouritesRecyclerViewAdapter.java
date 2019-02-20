package com.gmail.dp.denzay.nytviewer.adapters;

import com.gmail.dp.denzay.nytviewer.models.NewsContent;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;
import com.gmail.dp.denzay.nytviewer.views.OnListFragmentInteractionListener;

public class NewsItemFavouritesRecyclerViewAdapter extends NewsItemRecyclerViewAdapter {

    public NewsItemFavouritesRecyclerViewAdapter(NewsContent aNewsContent, OnListFragmentInteractionListener aListener) {
        super(aNewsContent, aListener);
    }

//    @BindingAdapter({"url", "onDownloadComplete"})
//    public static void doAsyncDownloadImage(ImageView view, String url, OnImageDownloadCompleteListener aListener) {
//        new AsyncDBImageDownloader(view.getContext(), aListener).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
//    }

    public NewsItem getItem(int aIndex) {
        return mData.getItems().get(aIndex);
    }

    public void removeItem(int aIndex) {
        mData.getItems().remove(aIndex);
    }
}
