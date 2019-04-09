package com.gmail.dp.denzay.nytviewer_mvvm

import android.app.Activity
import android.app.Application
import com.gmail.dp.denzay.nytviewer_mvvm.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class NYTViewerApp: Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .setContext(this)
            .build()
            .inject(this)
    }
}