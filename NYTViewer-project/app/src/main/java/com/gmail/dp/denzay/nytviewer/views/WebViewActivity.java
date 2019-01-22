package com.gmail.dp.denzay.nytviewer.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.gmail.dp.denzay.nytviewer.R;

public class WebViewActivity extends AppCompatActivity {

    public static final String KEY_URL = "URL";

    private WebView mWebView;

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
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }
}
