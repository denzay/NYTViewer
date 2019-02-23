package com.gmail.dp.denzay.nytviewer.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.gmail.dp.denzay.nytviewer.data.DBProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DBProviderModule {

    @Provides
    @Singleton
    @NonNull
    public DBProvider providesDBProvider(@NonNull Context aContext) {
        return new DBProvider();
    }
}
