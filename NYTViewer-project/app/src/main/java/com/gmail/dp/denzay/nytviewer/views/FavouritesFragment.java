package com.gmail.dp.denzay.nytviewer.views;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.adapters.NewsItemFavouritesRecyclerViewAdapter;
import com.gmail.dp.denzay.nytviewer.data.DBProvider;
import com.gmail.dp.denzay.nytviewer.data.FavouriteCachedContract.*;
import com.gmail.dp.denzay.nytviewer.models.NewsContent;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;
import com.gmail.dp.denzay.nytviewer.models.NewsItemFavourite;

public class FavouritesFragment extends Fragment {

    private NewsItemFavouritesRecyclerViewAdapter mAdapter;
    private NewsContent mNewsContent = new NewsContent();

    public FavouritesFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newsitem_list_favourites, container, false);
        getActivity().setTitle(R.string.action_favourites);
        mAdapter = new NewsItemFavouritesRecyclerViewAdapter(mNewsContent.getItems());
        return rootView;
    }

    private void LoadNewsContentFromDB() {
        Thread t = new Thread(() -> {
           DBProvider dbProvider = DBProvider.getInstance(FavouritesFragment.this.getContext());
           Cursor cursor = dbProvider.getSQL("SELECT * FROM " + FavouriteCachedEntry.TABLE_NAME);
           try {
               while(cursor.moveToNext()) {

                   long id = DBProvider.getLongValue(cursor, FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID);
                   String title = DBProvider.geStringValue(cursor, FavouriteCachedEntry.COLUMN_NAME_TITLE);
                   String description = DBProvider.geStringValue(cursor, FavouriteCachedEntry.COLUMN_NAME_DESCRIPTION);
                   String path = DBProvider.geStringValue(cursor, FavouriteCachedEntry.COLUMN_NAME_PATH);
                   NewsItem item = new NewsItemFavourite(id, path, title, description, null);
                   mNewsContent.addItem(item);
               }
           } finally {
               cursor.close();
           }
        });
        t.start();
    }

}
