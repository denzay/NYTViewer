package com.gmail.dp.denzay.nytviewer.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.gmail.dp.denzay.nytviewer.NYTViewerApp;
import com.gmail.dp.denzay.nytviewer.adapters.FavouritesDBAdapter;
import com.gmail.dp.denzay.nytviewer.models.NewsContent;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;
import com.gmail.dp.denzay.nytviewer.utils.CacheStorageUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DBNewsContentViewModel extends ViewModel {

    private NewsContent mNewsContent;
    @Inject
    FavouritesDBAdapter _mDBAdapter;
    @Inject
    CacheStorageUtils mCacheStorageUtils;

    private MutableLiveData<NewsContent> mNewsData;

    public DBNewsContentViewModel() {
        NYTViewerApp.getAppComponent().inject(this);
        mNewsContent = new NewsContent();
    }

    public LiveData<NewsContent> getData() {
        if (mNewsData == null) {
            mNewsData = new MutableLiveData<>();
            loadNewsFromDB();
        }
        return mNewsData;
    }

    // ToDo: как-то костыльно
    public void deleteNewsItem(long aNewsItemID) {
        Thread t = new Thread(() -> {
            String filePath = _mDBAdapter.getCachedNewsItemPath(aNewsItemID);
            mCacheStorageUtils.deleteFile(filePath);
            _mDBAdapter.deleteNewsItem(aNewsItemID);
            mNewsContent.deleteItemById(aNewsItemID);
        });
        t.start();

    }

    private void loadNewsFromDB() {
        Thread t = new Thread(() -> {
            List<NewsItem> newsItemList = new ArrayList<>();

            _mDBAdapter.loadNewsItems(newsItemList);
            for (NewsItem newsItem : newsItemList)
                mNewsContent.addItem(newsItem);
            mNewsData.postValue(mNewsContent);
        });
        t.start();
    }
}
