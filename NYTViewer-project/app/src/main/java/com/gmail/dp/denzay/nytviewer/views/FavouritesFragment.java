package com.gmail.dp.denzay.nytviewer.views;

import android.app.AlertDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gmail.dp.denzay.nytviewer.NYTViewerApp;
import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.adapters.NewsItemFavouritesRecyclerViewAdapter;
import com.gmail.dp.denzay.nytviewer.models.NewsContent;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;
import com.gmail.dp.denzay.nytviewer.view_models.DBNewsContentViewModel;

import javax.inject.Inject;

public class FavouritesFragment extends NewsListFragment {

    private static final String TAG_IS_SHOW_HINT = "IS_SHOW_HINT";
    private NewsItemFavouritesRecyclerViewAdapter mAdapter;

    @Inject
    SharedPreferences mSharedPreferences;

    public FavouritesFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newsitem_list_favourites, container, false);
        getActivity().setTitle(R.string.action_favourites);

        NYTViewerApp.getAppComponent().inject(this);

        NewsContent dummyNewsContent = new NewsContent();
        mAdapter = new NewsItemFavouritesRecyclerViewAdapter(dummyNewsContent, mListener);

        if (rootView instanceof RecyclerView) {
            RecyclerView recyclerView = ((RecyclerView) rootView);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            recyclerView.setAdapter(mAdapter);

            itemTouchHelper.attachToRecyclerView(recyclerView);
        }

        if (savedInstanceState == null) {
            showHint();
        }
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
                int i = viewHolder.getAdapterPosition();
                NewsItem newsItem = mAdapter.getItem(i);
                // ToDo: как-то костыльно
                DBNewsContentViewModel dbNewsContentViewModel = ViewModelProviders.of(FavouritesFragment.this).get(DBNewsContentViewModel.class);
                dbNewsContentViewModel.deleteNewsItem(newsItem.id);

                mAdapter.removeItem(i);
                mAdapter.notifyItemRemoved(i);
            });
            ad.show();
        }
    });

    private void LoadNewsContentFromDB() {
        DBNewsContentViewModel dbNewsContentViewModel = ViewModelProviders.of(this).get(DBNewsContentViewModel.class);
        LiveData<NewsContent> dbNewsContent = dbNewsContentViewModel.getData();
        // Подписываемся один раз, иначе плодятся колбеки
        if (!dbNewsContent.hasActiveObservers())
            dbNewsContent.observe(this, (@Nullable NewsContent aNewsContent) -> {
                mAdapter.updateData(aNewsContent);
            });
    }

    private void showHint() {
        // Отображаем подсказку один раз за время работы приложения
        boolean isShowHint = mSharedPreferences.getBoolean(TAG_IS_SHOW_HINT, true);
        if (!isShowHint) return;
        Toast.makeText(getContext(), R.string.hint_to_delete, Toast.LENGTH_LONG).show();
        mSharedPreferences.edit().putBoolean(TAG_IS_SHOW_HINT, false).apply();
    }

}
