package com.gmail.dp.denzay.nytviewer_mvvm.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.gmail.dp.denzay.nytviewer_mvvm.di.ViewModelKey
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_emailed.MostEmailedViewModel
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MostEmailedViewModel::class)
    fun bindUserViewModel(mostEmailedViewModel: MostEmailedViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(MostSharedViewModel::class)
//    fun bindSearchViewModel(mostSharedViewModel: MostSharedViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(MostViewedViewModel::class)
//    fun bindRepoViewModel(mostViewedViewModel: MostViewedViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
