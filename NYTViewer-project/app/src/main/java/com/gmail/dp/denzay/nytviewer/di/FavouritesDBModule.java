package com.gmail.dp.denzay.nytviewer.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.gmail.dp.denzay.nytviewer.adapters.FavouritesDBAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FavouritesDBModule {

    @Provides
    @Singleton
    @NonNull
    public FavouritesDBAdapter providesFavouritesDBAdapter(@NonNull Context aContext) {
        return new FavouritesDBAdapter(aContext);
    }
}
