package com.gmail.dp.denzay.nytviewer_mvvm.di.modules

import com.gmail.dp.denzay.nytviewer_mvvm.MainActivity
import com.gmail.dp.denzay.nytviewer_mvvm.data.most_emailed.MostEmailedRepositoryImpl
import com.gmail.dp.denzay.nytviewer_mvvm.data.most_shared.MostSharedRepositoryImpl
import com.gmail.dp.denzay.nytviewer_mvvm.data.most_viewed.MostViewedRepositoryImpl
import com.gmail.dp.denzay.nytviewer_mvvm.di.activity.ActivityScope
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedRepository
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedRepository
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedRepository
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(includes = [AndroidSupportInjectionModule::class])
interface AppModule {

    @Singleton
    @Binds
    fun provideMostEmailedRepository(repository: MostEmailedRepositoryImpl): MostEmailedRepository

    @Singleton
    @Binds
    fun provideMostSharedRepository(repository: MostSharedRepositoryImpl): MostSharedRepository

    @Singleton
    @Binds
    fun provideMostViewedRepository(repository: MostViewedRepositoryImpl): MostViewedRepository

    @ActivityScope
    @ContributesAndroidInjector
    fun mainActivityInjector(): MainActivity
}