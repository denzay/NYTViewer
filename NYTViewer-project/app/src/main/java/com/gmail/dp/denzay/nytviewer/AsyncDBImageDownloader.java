package com.gmail.dp.denzay.nytviewer;

import android.content.Context;
import android.graphics.Bitmap;

import com.gmail.dp.denzay.nytviewer.adapters.FavouritesDBAdapter;
import com.gmail.dp.denzay.nytviewer.adapters.NewsItemRecyclerViewAdapter;

public class AsyncDBImageDownloader extends AsyncImageDownloader {
    public AsyncDBImageDownloader(Context aContext, NewsItemRecyclerViewAdapter.OnImageDownloadCompleteListener aCallback) {
        super(aContext, aCallback);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return FavouritesDBAdapter.getInstance().loadNewsItemPicture(Long.valueOf(strings[0]));
    }

}
