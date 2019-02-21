package com.gmail.dp.denzay.nytviewer;

import android.content.Context;
import android.graphics.Bitmap;

import com.gmail.dp.denzay.nytviewer.adapters.FavouritesDBAdapter;
import com.gmail.dp.denzay.nytviewer.adapters.NewsItemRecyclerViewAdapter;

import javax.inject.Inject;

public class AsyncDBImageDownloader extends AsyncImageDownloader {
    @Inject
    FavouritesDBAdapter _mDBAdapter;

    public AsyncDBImageDownloader(Context aContext, NewsItemRecyclerViewAdapter.OnImageDownloadCompleteListener aCallback) {
        super(aContext, aCallback);
        NYTViewerApp.getAppComponent().inject(this);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return _mDBAdapter.loadNewsItemPicture(Long.valueOf(strings[0]));
    }

}
