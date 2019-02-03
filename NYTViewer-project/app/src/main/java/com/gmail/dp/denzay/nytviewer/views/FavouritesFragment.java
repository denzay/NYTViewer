package com.gmail.dp.denzay.nytviewer.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.adapters.CacheStorageAdapter;
import com.gmail.dp.denzay.nytviewer.adapters.FavouritesDBAdapter;
import com.gmail.dp.denzay.nytviewer.adapters.NewsItemFavouritesRecyclerViewAdapter;
import com.gmail.dp.denzay.nytviewer.models.NewsContent;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends NewsListFragment {

    private static final int MSG_LOAD_COMPLETE = 1;
    private static final int MSG_DELETE_COMPLETE = 2;
    private static final String TAG_IS_SHOW_HINT = "IS_SHOW_HINT";
    private static final String KEY_DB_DATA_LIST = "DB_DATA_LIST";

    private NewsItemFavouritesRecyclerViewAdapter mAdapter;
    private Handler mHandler;

    public FavouritesFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newsitem_list_favourites, container, false);
        getActivity().setTitle(R.string.action_favourites);

        if (savedInstanceState == null)
            mNewsContent = new NewsContent();
        else {
            mNewsContent = mRetainedDataFragment.getNewsContent();
        }

        mAdapter = new NewsItemFavouritesRecyclerViewAdapter(mNewsContent, mListener);

        if (rootView instanceof RecyclerView) {
            RecyclerView recyclerView = ((RecyclerView) rootView);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            recyclerView.setAdapter(mAdapter);

            itemTouchHelper.attachToRecyclerView(recyclerView);
        }

        mHandler = new Handler((Message aMsg) -> {
            switch (aMsg.what) {
                case MSG_LOAD_COMPLETE:
                    mAdapter.notifyDataSetChanged();
                    break;
                case MSG_DELETE_COMPLETE:
                    mAdapter.removeItem(aMsg.arg1);
                    mAdapter.notifyItemRemoved(aMsg.arg1);
                    break;
            }
            return true;
        });

        if (savedInstanceState == null) {
            LoadNewsContentFromDB();
            showHint();
        }
        return rootView;
    }

    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override

        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
            ad.setTitle(R.string.delete_dialog_title);
            ad.setMessage(R.string.confirm_delete);
            ad.setCancelable(true);

            ad.setNegativeButton(R.string.Cancel, (DialogInterface dialog, int which) -> {
                // Восстанавливаем обратно айтем, в случае отмены
                int i = viewHolder.getAdapterPosition();
                mAdapter.notifyItemChanged(i);
            });

            ad.setPositiveButton(R.string.OK, (DialogInterface dialog, int which) -> {
                Thread t = new Thread(() -> {
                    int i = viewHolder.getAdapterPosition();
                    NewsItem newsItem = mAdapter.getItem(i);

                    FavouritesDBAdapter dbAdapter = FavouritesDBAdapter.getInstance();
                    String filePath = dbAdapter.getCachedNewsItemPath(newsItem.id);

                    CacheStorageAdapter.deleteFile(filePath);
                    dbAdapter.deleteNewsItem(newsItem.id);

                    Message msg = new Message();
                    msg.what = MSG_DELETE_COMPLETE;
                    msg.arg1 = i;
                    mHandler.sendMessage(msg);
                });
                t.start();
            });
            ad.show();
        }
    });

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

    private void showHint() {
        // Отображаем подсказку один раз за время работы приложения
        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        if(prefs == null) return;
        boolean isShowHint = prefs.getBoolean(TAG_IS_SHOW_HINT, true);
        if (!isShowHint) return;
        Toast.makeText(getContext(), R.string.hint_to_delete, Toast.LENGTH_LONG).show();
        prefs.edit().putBoolean(TAG_IS_SHOW_HINT, false).apply();
    }

    @Override
    protected String getRetainedDataFragmentTag() {
        return KEY_DB_DATA_LIST;
    }

}
