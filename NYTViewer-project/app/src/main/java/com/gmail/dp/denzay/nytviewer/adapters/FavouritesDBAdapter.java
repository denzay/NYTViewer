package com.gmail.dp.denzay.nytviewer.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import com.gmail.dp.denzay.nytviewer.data.DBProvider;
import com.gmail.dp.denzay.nytviewer.data.FavouriteCachedContract;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;


public class FavouritesDBAdapter {

    private static FavouritesDBAdapter mInstance;
    private static DBProvider mDBProvider;

    public synchronized static FavouritesDBAdapter getInstance() {
        if (mInstance == null)
            mInstance = new FavouritesDBAdapter();
        return mInstance;
    }

    private FavouritesDBAdapter() {
    }

    public synchronized void connect(Context aContext) {
        if (mDBProvider == null)
            mDBProvider = DBProvider.getInstance(aContext);
        if (!mDBProvider.isConnected())
            mDBProvider.connect();
    }

    public synchronized void disconnect() {
        if (mDBProvider != null) {
            mDBProvider.disconnect();
            mDBProvider = null;
        }
        mInstance = null;
    }

    public void saveNewsItem(NewsItem aNewsItem, String aFilePath) {
        ContentValues values = new ContentValues();
        values.put(FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID, aNewsItem.id);
        values.put(FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_TITLE, aNewsItem.title);
        values.put(FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_DESCRIPTION, aNewsItem.shortDescription);
        values.put(FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_PATH, aFilePath);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        aNewsItem.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        values.put(FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_PICTURE, byteArray);
        mDBProvider.insertValues(FavouriteCachedContract.FavouriteCachedEntry.TABLE_NAME, values);
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
        Cursor cursor = mDBProvider.getSQL("SELECT * FROM " + FavouriteCachedContract.FavouriteCachedEntry.TABLE_NAME);
        try {
            while (cursor.moveToNext()) {
                long id = DBProvider.getLongValue(cursor, FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID);
                String title = DBProvider.geStringValue(cursor, FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_TITLE);
                String description = DBProvider.geStringValue(cursor, FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_DESCRIPTION);
                String path = DBProvider.geStringValue(cursor, FavouriteCachedContract.FavouriteCachedEntry.COLUMN_NAME_PATH);
                NewsItem item = new NewsItem(id, path, title, description, null);
                aNewsItemsList.add(item);
            }
        } finally {
            cursor.close();
        }
    }

}
