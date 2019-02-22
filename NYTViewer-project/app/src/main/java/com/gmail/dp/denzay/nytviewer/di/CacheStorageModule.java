package com.gmail.dp.denzay.nytviewer.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.gmail.dp.denzay.nytviewer.utils.CacheStorageUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheStorageModule {

    @Provides
    @Singleton
    @NonNull
    public CacheStorageUtils providesCacheStorageUtils(Context aContext) {
        return new CacheStorageUtils(aContext);
    }
}
