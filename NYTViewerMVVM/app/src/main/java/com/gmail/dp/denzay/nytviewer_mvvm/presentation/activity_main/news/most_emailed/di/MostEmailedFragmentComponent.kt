package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_emailed.di

import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_emailed.MostEmailedContract
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.MainRouter
import dagger.Binds
import dagger.Module
import dagger.Subcomponent


@Subcomponent(modules = [MostEmailedFragmentComponent.MainModule::class])
interface MostEmailedFragmentComponent {
    @Module
    interface MainModule {

        @Binds
        fun provideRouter(router: MainRouter): MostEmailedContract.Router

//        @Binds
//        fun providePresenter(presenter: BillingPresenter): MostEmailedContract.Presenter
    }
}