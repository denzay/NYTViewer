package com.gmail.dp.denzay.nytviewer.views;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.adapters.CacheStorageAdapter;
import com.gmail.dp.denzay.nytviewer.adapters.FavouritesDBAdapter;
import com.gmail.dp.denzay.nytviewer.adapters.NewsItemFavouritesRecyclerViewAdapter;
import com.gmail.dp.denzay.nytviewer.models.NewsContent;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;

import java.util.ArrayList;
import java.util.List;

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
        if (rootView instanceof RecyclerView) {
            RecyclerView recyclerView = ((RecyclerView) rootView);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            recyclerView.setAdapter(mAdapter);

            itemTouchHelper.attachToRecyclerView(recyclerView);
        }

        mHandler = new Handler((Message aMsg) -> {
            if (aMsg.what == MSG_LOAD_COMPLETE)
                mAdapter.notifyDataSetChanged();
            return true;
        });

        LoadNewsContentFromDB();
        return rootView;
    }

    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override

        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            int i = viewHolder.getAdapterPosition();
            NewsItem newsItem = mAdapter.getItem(i);

            FavouritesDBAdapter dbAdapter = FavouritesDBAdapter.getInstance();
            String filePath = dbAdapter.getCachedNewsItemPath(newsItem.id);

            CacheStorageAdapter.deleteFile(filePath);
            dbAdapter.deleteNewsItem(newsItem.id);
            mAdapter.removeItem(i);
            mAdapter.notifyItemRemoved(i);
        }
    });

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
           List<NewsItem> newsItemList = new ArrayList<>();
           FavouritesDBAdapter.getInstance().loadNewsItems(newsItemList);
           for (NewsItem newsItem : newsItemList)
               mNewsContent.addItem(newsItem);
           mHandler.sendEmptyMessage(MSG_LOAD_COMPLETE);
        });
        t.start();
    }

}
