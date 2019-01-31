package com.gmail.dp.denzay.nytviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.gmail.dp.denzay.nytviewer.adapters.NewsItemRecyclerViewAdapter;
import com.gmail.dp.denzay.nytviewer.data.DBProvider;
import com.gmail.dp.denzay.nytviewer.data.FavouriteCachedContract.FavouriteCachedEntry;

import java.io.ByteArrayInputStream;

public class AsyncDBImageDownloader extends AsyncImageDownloader {
    public AsyncDBImageDownloader(Context aContext, NewsItemRecyclerViewAdapter.OnImageDownloadCompleteListener aCallback) {
        super(aContext, aCallback);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap result = null;
        String articleId = strings[0];
        Context context = mContext.get();
        if (context != null) {
            DBProvider dbProvider = DBProvider.getInstance(context);
            byte[] picByteArray = dbProvider.DBLookupBlob(FavouriteCachedEntry.TABLE_NAME,
                            FavouriteCachedEntry.COLUMN_NAME_PICTURE,
                    FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID + " = " + articleId);
            if (picByteArray != null) {
                ByteArrayInputStream picStream = new ByteArrayInputStream(picByteArray);
                result = BitmapFactory.decodeStream(picStream);
            }
        }
        return result;
    }
}
