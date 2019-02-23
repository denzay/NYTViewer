package com.gmail.dp.denzay.nytviewer.di;

import android.support.annotation.NonNull;

import com.gmail.dp.denzay.nytviewer.adapters.FavouritesDBAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FavouritesDBAdapterModule {

    @Provides
    @Singleton
    @NonNull
    public FavouritesDBAdapter providesFavouritesDBAdapter() {
        return new FavouritesDBAdapter();
    }
}
