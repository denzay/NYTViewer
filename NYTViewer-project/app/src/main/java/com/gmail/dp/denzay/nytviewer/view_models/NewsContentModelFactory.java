package com.gmail.dp.denzay.nytviewer.view_models;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class NewsContentModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final NewsContentViewModel.NewsType mNewsType;
    private final Application mApplication;

    public NewsContentModelFactory(Application aApplication, NewsContentViewModel.NewsType aNewsType) {
        super();
        mApplication = aApplication;
        mNewsType = aNewsType;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == NewsContentViewModel.class) {
            return (T) new NewsContentViewModel(mApplication, mNewsType);
        }
        return null;
    }
}
