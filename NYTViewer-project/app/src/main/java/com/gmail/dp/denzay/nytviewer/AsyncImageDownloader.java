package com.gmail.dp.denzay.nytviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncImageDownloader extends AsyncTask<String, Void, Bitmap> {

    private final WeakReference<ImageView> mImgView;

    public AsyncImageDownloader(ImageView aImgView) {
        mImgView = new WeakReference<ImageView>(aImgView);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return downloadBitmap(strings[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (isCancelled()) {
            bitmap = null;
        }
        if (mImgView != null) {
            ImageView imageView = mImgView.get();
            if (imageView != null) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                } else {
                    setDefaultImage(imageView, R.drawable.user_placeholder);
                }
            }
        }
    }

    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;

        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            if (mImgView != null) {
                ImageView imageView = mImgView.get();
                setDefaultImage(imageView, R.drawable.user_placeholder_error);
            }
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    private void setDefaultImage(ImageView aImageView, @DrawableRes int aResId) {
        if (aImageView == null) return;
        Drawable placeholder = aImageView.getContext().getResources().getDrawable(aResId);
        aImageView.setImageDrawable(placeholder);
    }
}
