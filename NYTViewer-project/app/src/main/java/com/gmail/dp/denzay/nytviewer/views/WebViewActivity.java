package com.gmail.dp.denzay.nytviewer.views;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.gmail.dp.denzay.nytviewer.NYTViewerApp;
import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.adapters.FavouritesDBAdapter;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;
import com.gmail.dp.denzay.nytviewer.utils.CacheStorageUtils;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;

public class WebViewActivity extends AppCompatActivity {

    public static final String KEY_NEWS_ITEM = "NEWS_ITEM";
    public static final String KEY_IS_CACHED_ITEM = "IS_CACHED_ITEM";
    private static final String KEY_IS_FAVOURITE = "IS_FAVOURITE";

    private WebView mWebView;
    private ProgressBar mProgressBar;
    private AtomicBoolean mIsFavourite = new AtomicBoolean(false);
    private NewsItem mNewsItem;
    private Handler mHandler;
    private boolean mIsCachedItem;
    private boolean mIsPageDownloaded = false;
    @Inject
    FavouritesDBAdapter _mDBAdapter;
    @Inject
    CacheStorageUtils mCacheStorageUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = findViewById(R.id.wb_WebView);
        mProgressBar = findViewById(R.id.pb_WebView);

        NYTViewerApp.getAppComponent().inject(this);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
                if(newProgress == 100){
                    mIsPageDownloaded = true;
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        Bundle arguments = getIntent().getExtras();
        mNewsItem = arguments.getParcelable(KEY_NEWS_ITEM);
        mIsCachedItem = arguments.getBoolean(KEY_IS_CACHED_ITEM);

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
                if (!mIsCachedItem)
                    setPageCached();
                mWebView.loadUrl(mIsCachedItem ? "file:///" + mNewsItem.url : mNewsItem.url);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
        outState.putBoolean(KEY_IS_FAVOURITE, mIsFavourite.get());
    }

    // инжектится в главном потоке, юзается в фоновых. Поэтому synchronized getter
    private synchronized FavouritesDBAdapter getDBAdapter() {
        return _mDBAdapter;
    }

    private void updateFavouriteMenuIcon(MenuItem aMenuItem) {
        if (mIsFavourite.get())
            aMenuItem.setIcon(R.drawable.ic_star);
        else
            aMenuItem.setIcon(R.drawable.ic_star_border);
    }

    private void saveWebPageToCache() {
        if (!mIsPageDownloaded) {
            Snackbar.make(mWebView, R.string.warning_save_page, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.warning_got_it, (View v) -> {
                    })
                    .show();

            mIsFavourite.set(false);
            invalidateOptionsMenu();
            return;
        }

        String fileName = mCacheStorageUtils.getExternalFolderPath();

        mWebView.saveWebArchive(fileName, true, (String value) -> {
            if (value == null) return; // ошибка сохранения веб архива (операция прервана)
            Thread t = new Thread(() -> {
                getDBAdapter().saveNewsItem(mNewsItem, value);
            });
            t.start();
        });
    }

    private void deleteWebPageFromCache() {
        Thread t = new Thread(() -> {
            FavouritesDBAdapter dbAdapter = getDBAdapter();
            String filePath = getDBAdapter().getCachedNewsItemPath(mNewsItem.id);

            mCacheStorageUtils.deleteFile(filePath);
            dbAdapter.deleteNewsItem(mNewsItem.id);
        });
        t.start();
    }

    private void setPageCached() {
        Thread t = new Thread(() -> {
            boolean result = false;
            String filePath = getDBAdapter().getCachedNewsItemPath(mNewsItem.id);
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
        if (mIsCachedItem)
            return true;
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        MenuItem menuItem = menu.getItem(0);
        updateFavouriteMenuIcon(menuItem);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!mIsCachedItem)
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
