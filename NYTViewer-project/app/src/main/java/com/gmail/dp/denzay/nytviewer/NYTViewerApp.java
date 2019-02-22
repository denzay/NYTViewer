package com.gmail.dp.denzay.nytviewer;

import android.app.Application;

import com.gmail.dp.denzay.nytviewer.di.AppComponent;
import com.gmail.dp.denzay.nytviewer.di.AppModule;
import com.gmail.dp.denzay.nytviewer.di.DaggerAppComponent;

public class NYTViewerApp extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }


    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

}
