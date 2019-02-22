package com.gmail.dp.denzay.nytviewer.di;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.gmail.dp.denzay.nytviewer.data.FavouriteCachedDBHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FavouriteCachedDBHelperModule {

    @Provides
    @Singleton
    @NonNull
    public SQLiteOpenHelper providesFavouriteCachedDBHelperModule(@NonNull Context aContext) {
        return new FavouriteCachedDBHelper(aContext);
    }
}
