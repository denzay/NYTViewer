package com.gmail.dp.denzay.nytviewer.di;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context mContext;

    public AppModule (@NonNull Context aContext) {
        mContext = aContext;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }
}
