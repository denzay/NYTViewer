package com.gmail.dp.denzay.nytviewer_mvvm.di.modules

import dagger.Module
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.MainRouter
import dagger.Binds
import com.gmail.dp.denzay.nytviewer_mvvm.di.activity.ActivityScope

@Module
interface MainActivityModule {

    @ActivityScope
    @Binds
    fun router(mainRouter: MainRouter): MainRouter
}