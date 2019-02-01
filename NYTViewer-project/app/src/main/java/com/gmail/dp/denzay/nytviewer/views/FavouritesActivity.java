package com.gmail.dp.denzay.nytviewer.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;

public class FavouritesActivity extends AppCompatActivity implements OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
    }

    @Override
    public void onListFragmentInteraction(NewsItem item) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.KEY_NEWS_ITEM, item);
        intent.putExtra(WebViewActivity.KEY_IS_CACHED_ITEM,  true);
        startActivity(intent);
    }
}
