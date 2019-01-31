package com.gmail.dp.denzay.nytviewer.views;


import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.adapters.NewsItemFavouritesRecyclerViewAdapter;
import com.gmail.dp.denzay.nytviewer.data.DBProvider;
import com.gmail.dp.denzay.nytviewer.data.FavouriteCachedContract.*;
import com.gmail.dp.denzay.nytviewer.models.NewsContent;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;

import java.io.ByteArrayInputStream;

public class FavouritesFragment extends Fragment {

    private static final int MSG_LOAD_COMPLETE = 1;

    private NewsItemFavouritesRecyclerViewAdapter mAdapter;
    private OnListFragmentInteractionListener mListener;
    private Handler mHandler;
    private NewsContent mNewsContent = new NewsContent();

    public FavouritesFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newsitem_list_favourites, container, false);
        getActivity().setTitle(R.string.action_favourites);

        mAdapter = new NewsItemFavouritesRecyclerViewAdapter(mNewsContent.getItems(), mListener);
        if (rootView instanceof RecyclerView)
            ((RecyclerView) rootView).setAdapter(mAdapter);

        mHandler = new Handler((Message aMsg) -> {
            if (aMsg.what == MSG_LOAD_COMPLETE)
                mAdapter.notifyDataSetChanged();
            return true;
        });

        LoadNewsContentFromDB();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
                   NewsItem item = new NewsItem(id, path, title, description, null);
                   mNewsContent.addItem(item);
               }
           } finally {
               cursor.close();
               mHandler.sendEmptyMessage(MSG_LOAD_COMPLETE);
           }
        });
        t.start();
    }

}
