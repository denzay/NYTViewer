package com.gmail.dp.denzay.nytviewer.di;

import com.gmail.dp.denzay.nytviewer.AsyncDBImageDownloader;
import com.gmail.dp.denzay.nytviewer.MainActivity;
import com.gmail.dp.denzay.nytviewer.views.FavouritesFragment;
import com.gmail.dp.denzay.nytviewer.views.WebViewActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, CacheStorageModule.class, FavouritesDBModule.class, FavouriteCachedDBHelperModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity aMainActivity);
    void inject(WebViewActivity aWebViewActivity);
    void inject(AsyncDBImageDownloader aAsyncDBImageDownloader);
    void inject(FavouritesFragment aFavouritesFragment);
}
