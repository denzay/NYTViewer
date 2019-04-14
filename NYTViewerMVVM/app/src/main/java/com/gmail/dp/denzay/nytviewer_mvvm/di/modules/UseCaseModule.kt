package com.gmail.dp.denzay.nytviewer_mvvm.di.modules

import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedInteractor
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedUseCase
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedInteractor
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedUseCase
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedInteractor
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedUseCase
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindMostEmailedUseCase(interactor: MostEmailedInteractor): MostEmailedUseCase

    @Binds
    fun bindMostSharedUseCase(interactor: MostSharedInteractor): MostSharedUseCase

    @Binds
    fun bindMostViewedUseCase(interactor: MostViewedInteractor): MostViewedUseCase
}