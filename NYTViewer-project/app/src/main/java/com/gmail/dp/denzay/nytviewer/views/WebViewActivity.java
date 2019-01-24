package com.gmail.dp.denzay.nytviewer.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.gmail.dp.denzay.nytviewer.R;

public class WebViewActivity extends AppCompatActivity {

    public static final String KEY_URL = "URL";

    private WebView mWebView;
    private boolean mIsFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = findViewById(R.id.wb_WebView);

        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
        } else {
            Bundle arguments = getIntent().getExtras();
            String url = arguments.get(KEY_URL).toString();
            if (url != "") {
                mWebView.loadUrl(url);
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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
