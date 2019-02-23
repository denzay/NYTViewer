package com.gmail.dp.denzay.nytviewer.adapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import com.gmail.dp.denzay.nytviewer.NYTViewerApp;
import com.gmail.dp.denzay.nytviewer.data.DBProvider;
import com.gmail.dp.denzay.nytviewer.data.FavouriteCachedContract;
import com.gmail.dp.denzay.nytviewer.data.FavouriteCachedContract.FavouriteCachedEntry;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.inject.Inject;


public class FavouritesDBAdapter {

    @Inject
    DBProvider mDBProvider;

    public synchronized void connect() {
        NYTViewerApp.getAppComponent().inject(this);
        if (!mDBProvider.isConnected())
            mDBProvider.connect();
    }

    public synchronized void disconnect() {
        if (mDBProvider != null) {
            mDBProvider.disconnect();
        }
        mDBProvider = null;
    }

    public void saveNewsItem(NewsItem aNewsItem, String aFilePath) {
        ContentValues values = new ContentValues();
        values.put(FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID, aNewsItem.id);
        values.put(FavouriteCachedEntry.COLUMN_NAME_TITLE, aNewsItem.title);
        values.put(FavouriteCachedEntry.COLUMN_NAME_DESCRIPTION, aNewsItem.shortDescription);
        values.put(FavouriteCachedEntry.COLUMN_NAME_PATH, aFilePath);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        aNewsItem.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        values.put(FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_PICTURE, byteArray);

        String whereClause = FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID + " = " + aNewsItem.id;
        if (mDBProvider.DBExists(FavouriteCachedEntry.TABLE_NAME, whereClause))
            mDBProvider.updateValues(FavouriteCachedEntry.TABLE_NAME, values, whereClause);
        else
            mDBProvider.insertValues(FavouriteCachedEntry.TABLE_NAME, values);
    }

    public void deleteNewsItem(long aNewsItemId) {
        String whereClause = FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID + " = " + aNewsItemId;
        mDBProvider.deleteValues(FavouriteCachedContract.FavouriteCachedEntry.TABLE_NAME, whereClause);
    }

    @Nullable
    public String getCachedNewsItemPath(long aNewsItemId) {
        String whereClause = FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID + " = " + aNewsItemId;
        return mDBProvider.DBLookup(FavouriteCachedContract.FavouriteCachedEntry.TABLE_NAME, FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_PATH, whereClause);
    }

    @Nullable
    public Bitmap loadNewsItemPicture(long aNewsItemId) {
        Bitmap result = null;
        byte[] picByteArray = mDBProvider.DBLookupBlob(FavouriteCachedContract.FavouriteCachedEntry.TABLE_NAME,
                FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_PICTURE,
                FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID + " = " + aNewsItemId);

        if (picByteArray != null) {
            ByteArrayInputStream picStream = new ByteArrayInputStream(picByteArray);
            result = BitmapFactory.decodeStream(picStream);
        }
        return result;
    }

    public void loadNewsItems(List<NewsItem> aNewsItemsList) {
        try (Cursor cursor = mDBProvider.getSQL("SELECT * FROM " + FavouriteCachedContract.FavouriteCachedEntry.TABLE_NAME)) {
            while (cursor.moveToNext()) {
                long id = DBProvider.getLongValue(cursor, FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID);
                String title = DBProvider.geStringValue(cursor, FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_TITLE);
                String description = DBProvider.geStringValue(cursor, FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_DESCRIPTION);
                String path = DBProvider.geStringValue(cursor, FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_PATH);
                NewsItem item = new NewsItem(id, path, title, description, null);
                aNewsItemsList.add(item);
            }
        }
    }

}
