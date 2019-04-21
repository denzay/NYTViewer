package com.gmail.dp.denzay.nytviewer_mvvm.di.modules

import com.gmail.dp.denzay.nytviewer_mvvm.data.most_emailed.MostEmailedRepositoryImpl
import com.gmail.dp.denzay.nytviewer_mvvm.data.most_shared.MostSharedRepositoryImpl
import com.gmail.dp.denzay.nytviewer_mvvm.data.most_viewed.MostViewedRepositoryImpl
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedRepository
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedRepository
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoriesModule {

    @Binds
    @Singleton
    fun bindMostEmailedRepository(repository: MostEmailedRepositoryImpl): MostEmailedRepository

    @Binds
    @Singleton
    fun bindMostSharedRepository(repository: MostSharedRepositoryImpl): MostSharedRepository

    @Binds
    @Singleton
    fun bindMostViewedRepository(repository: MostViewedRepositoryImpl): MostViewedRepository
}