package com.gmail.dp.denzay.nytviewer.views;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.data.DBProvider;
import com.gmail.dp.denzay.nytviewer.data.FavouriteCachedContract.FavouriteCachedEntry;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class WebViewActivity extends AppCompatActivity {

    public static final String KEY_NEWS_ITEM = "NEWS_ITEM";
    private static final String KEY_IS_FAVOURITE = "IS_FAVOURITE";

    private WebView mWebView;
    private AtomicBoolean mIsFavourite = new AtomicBoolean(false);
    private NewsItem mNewsItem;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = findViewById(R.id.wb_WebView);

        Bundle arguments = getIntent().getExtras();
        mNewsItem = arguments.getParcelable(KEY_NEWS_ITEM);

        mHandler = new Handler((Message aMsg) -> {
            mIsFavourite.set(aMsg.what == 1);
            invalidateOptionsMenu();
            return true;
        });

        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
            mIsFavourite.set(savedInstanceState.getBoolean(KEY_IS_FAVOURITE));
            invalidateOptionsMenu();
        } else {
            if ((mNewsItem != null) && (mNewsItem.url != "")) {
                setPageCached();
                mWebView.loadUrl(mNewsItem.url);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
        outState.putBoolean(KEY_IS_FAVOURITE, mIsFavourite.get());
    }

    private void updateFavouriteMenuIcon(MenuItem aMenuItem) {
        if (mIsFavourite.get())
            aMenuItem.setIcon(R.drawable.ic_star);
        else
            aMenuItem.setIcon(R.drawable.ic_star_border);
    }

    private void saveWebPageToCache() {
        String fileName = getExternalFilesDir(null).getAbsolutePath() + "/";
        mWebView.saveWebArchive(fileName, true, (String value) -> {
            Thread t = new Thread(() -> {
                ContentValues values = new ContentValues();
                values.put(FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID, mNewsItem.id);
                values.put(FavouriteCachedEntry.COLUMN_NAME_TITLE, mNewsItem.title);
                values.put(FavouriteCachedEntry.COLUMN_NAME_DESCRIPTION, mNewsItem.shortDescription);
                values.put(FavouriteCachedEntry.COLUMN_NAME_PATH, value);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                mNewsItem.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                values.put(FavouriteCachedEntry.COLUMN_NAME_PICTURE, byteArray);
                DBProvider.getInstance(WebViewActivity.this).insertValues(FavouriteCachedEntry.TABLE_NAME, values);
            });
            t.start();
        });
    }

    private void deleteWebPageFromCache() {
        Thread t = new Thread(() -> {
            String whereClause = FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID + " = " + mNewsItem.id;

            DBProvider dbProvider = DBProvider.getInstance(WebViewActivity.this);
            String filePath = dbProvider.DBLookup(FavouriteCachedEntry.TABLE_NAME, FavouriteCachedEntry.COLUMN_NAME_PATH, whereClause);
            if (filePath == null) return;
            File f = new File(filePath);
            if (!Environment.getExternalStorageState(f).equals(Environment.MEDIA_MOUNTED)) return;
            if (f.exists())
                f.delete();
            dbProvider.deleteValues(FavouriteCachedEntry.TABLE_NAME, whereClause);
        });
        t.start();
    }

    private void setPageCached() {
        Thread t = new Thread(() -> {
            boolean result = false;
            String whereClause = FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID + " = " + mNewsItem.id;
            DBProvider dbProvider = DBProvider.getInstance(WebViewActivity.this);
            String filePath = dbProvider.DBLookup(FavouriteCachedEntry.TABLE_NAME, FavouriteCachedEntry.COLUMN_NAME_PATH, whereClause);
            if (filePath != null) {
                File f = new File(filePath);
                if (Environment.getExternalStorageState(f).equals(Environment.MEDIA_MOUNTED))
                    result = f.exists();
            }
            mHandler.sendEmptyMessage(result ? 1 : 0);
        });
        t.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        MenuItem menuItem = menu.getItem(0);
        updateFavouriteMenuIcon(menuItem);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        updateFavouriteMenuIcon(menu.getItem(0));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add_to_favorites:
                mIsFavourite.set(!mIsFavourite.get());
                updateFavouriteMenuIcon(item);
                if (mIsFavourite.get())
                    saveWebPageToCache();
                else
                    deleteWebPageFromCache();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
