package com.gmail.dp.denzay.nytviewer_mvvm.di.modules

import com.gmail.dp.denzay.nytviewer_mvvm.data.most_emailed.MostEmailedRepositoryImpl
import com.gmail.dp.denzay.nytviewer_mvvm.data.most_shared.MostSharedRepositoryImpl
import com.gmail.dp.denzay.nytviewer_mvvm.data.most_viewed.MostViewedRepositoryImpl
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedRepository
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedRepository
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoriesModule {

    @Binds
    fun bindMostEmailedRepository(repository: MostEmailedRepositoryImpl): MostEmailedRepository

    @Binds
    fun bindMostSharedRepository(repository: MostSharedRepositoryImpl): MostSharedRepository

    @Binds
    fun bindMostViewedRepository(repository: MostViewedRepositoryImpl): MostViewedRepository
}