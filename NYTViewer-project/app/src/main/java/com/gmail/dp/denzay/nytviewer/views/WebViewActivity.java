package com.gmail.dp.denzay.nytviewer.views;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.data.FavouriteCachedDBProvider;
import com.gmail.dp.denzay.nytviewer.data.FavouriteCachedContract.FavouriteCachedEntry;
import com.gmail.dp.denzay.nytviewer.models.NewsContent.NewsItem;

public class WebViewActivity extends AppCompatActivity {

    public static final String KEY_NEWS_ITEM = "NEWS_ITEM";

    private WebView mWebView;
    private boolean mIsFavourite;
    private NewsItem mNewsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = findViewById(R.id.wb_WebView);

        Bundle arguments = getIntent().getExtras();
        mNewsItem = arguments.getParcelable(KEY_NEWS_ITEM);

        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
        } else {
            if ((mNewsItem != null) && (mNewsItem.url != "")) {
                mWebView.loadUrl(mNewsItem.url);
            }
        }
        mIsFavourite = false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }

    private void updateFavouriteMenuIcon(MenuItem aMenuItem) {
        if (mIsFavourite)
            aMenuItem.setIcon(R.drawable.ic_star);
        else
            aMenuItem.setIcon(R.drawable.ic_star_border);
    }

    private void saveWebPageToCache() {
        String fileName = getExternalFilesDir(null).getAbsolutePath() + "/";

        mWebView.saveWebArchive(fileName, true, (String value) -> {
            ContentValues values = new ContentValues();
            values.put(FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID, mNewsItem.id);
            values.put(FavouriteCachedEntry.COLUMN_NAME_TITLE, mNewsItem.title);
            values.put(FavouriteCachedEntry.COLUMN_NAME_PATH, value);
            FavouriteCachedDBProvider.getInstance(this).insertValues(FavouriteCachedEntry.TABLE_NAME, values);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        MenuItem menuItem =  menu.getItem(0);
        updateFavouriteMenuIcon(menuItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add_to_favorites:
                mIsFavourite = !mIsFavourite;
                updateFavouriteMenuIcon(item);
                if (mIsFavourite)
                    saveWebPageToCache();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
