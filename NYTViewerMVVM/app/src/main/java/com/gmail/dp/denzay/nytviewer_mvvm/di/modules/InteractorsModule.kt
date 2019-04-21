package com.gmail.dp.denzay.nytviewer_mvvm.di.modules

import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedInteractor
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedRepository
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedUseCase
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedInteractor
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedRepository
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedUseCase
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedInteractor
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedRepository
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorsModule {

    @Provides
    @Singleton
    fun provideMostEmailedUseCase(mostEmailedRepository: MostEmailedRepository): MostEmailedUseCase {
        return MostEmailedInteractor(mostEmailedRepository)
    }

    @Provides
    @Singleton
    fun provideMostSharedUseCase(mostSharedRepository: MostSharedRepository): MostSharedUseCase {
        return MostSharedInteractor(mostSharedRepository)
    }

    @Provides
    @Singleton
    fun provideMostViewedUseCase(mostViewedRepository: MostViewedRepository): MostViewedUseCase {
        return MostViewedInteractor(mostViewedRepository)
    }
}