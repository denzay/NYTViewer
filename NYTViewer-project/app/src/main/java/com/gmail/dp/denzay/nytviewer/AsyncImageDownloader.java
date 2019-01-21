package com.gmail.dp.denzay.nytviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.DrawableRes;
import android.util.LruCache;

import com.gmail.dp.denzay.nytviewer.adapters.NewsItemRecyclerViewAdapter;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncImageDownloader extends AsyncTask<String, Void, Bitmap> {

    private final static int CACHE_SIZE = 50 * 1024 * 1024; // 50 MiB
    private final WeakReference<Context> mContext;
    private final WeakReference<NewsItemRecyclerViewAdapter.OnImageDownloadCompleteListener> mCallback;
    private final static LruCache<String, Bitmap> memoryCache;

    static {
        memoryCache = new LruCache<String, Bitmap>(CACHE_SIZE) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public AsyncImageDownloader(Context aContext, NewsItemRecyclerViewAdapter.OnImageDownloadCompleteListener aCallback) {
        mContext = new WeakReference<Context>(aContext);
        mCallback = new WeakReference<NewsItemRecyclerViewAdapter.OnImageDownloadCompleteListener>(aCallback);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String aURl = strings[0];
        Bitmap result;
        result = memoryCache.get(aURl);
        if (result == null)
            result = downloadBitmap(aURl);
        return result;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (isCancelled()) {
            bitmap = null;
        }

        NewsItemRecyclerViewAdapter.OnImageDownloadCompleteListener callback = mCallback.get();
        if (callback != null) {
            if (bitmap != null) {
                callback.OnImageDownloadComplete(bitmap);
            } else {
                returnDefaultImage(R.drawable.user_placeholder);
            }
        }
    }

    private Bitmap downloadBitmap(String aUrl) {
        HttpURLConnection urlConnection = null;

        try {
            URL uri = new URL(aUrl);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                returnDefaultImage(R.drawable.user_placeholder_error);
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                memoryCache.put(aUrl, bitmap);
                return bitmap;
            }
        } catch (Exception e) {
            returnDefaultImage(R.drawable.user_placeholder_error);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    private void returnDefaultImage(@DrawableRes int aResId) {
        Context context = mContext.get();
        NewsItemRecyclerViewAdapter.OnImageDownloadCompleteListener callback = mCallback.get();
        if ((callback == null) || (context == null)) return;

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), aResId);
        callback.OnImageDownloadComplete(bitmap);
    }
}
