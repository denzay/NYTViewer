package com.gmail.dp.denzay.nytviewer_mvvm.di.modules

import com.gmail.dp.denzay.nytviewer_mvvm.data.most_emailed.MostEmailedRemoteStorage
import com.gmail.dp.denzay.nytviewer_mvvm.data.most_emailed.MostEmailedRepositoryImpl
import com.gmail.dp.denzay.nytviewer_mvvm.data.most_shared.MostSharedRemoteStorage
import com.gmail.dp.denzay.nytviewer_mvvm.data.most_shared.MostSharedRepositoryImpl
import com.gmail.dp.denzay.nytviewer_mvvm.data.most_viewed.MostViewedRemoteStorage
import com.gmail.dp.denzay.nytviewer_mvvm.data.most_viewed.MostViewedRepositoryImpl
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedRepository
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedRepository
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideMostEmailedRepository(remoteStorage: MostEmailedRemoteStorage): MostEmailedRepository {
        return MostEmailedRepositoryImpl(remoteStorage)
    }

    @Provides
    @Singleton
    fun provideMostSharedRepository(remoteStorage: MostSharedRemoteStorage): MostSharedRepository {
        return MostSharedRepositoryImpl(remoteStorage)
    }

    @Provides
    @Singleton
    fun provideMostViewedRepository(remoteStorage: MostViewedRemoteStorage): MostViewedRepository {
        return MostViewedRepositoryImpl(remoteStorage)
    }

}