package com.gmail.dp.denzay.nytviewer_mvvm.di.modules

import com.gmail.dp.denzay.nytviewer_mvvm.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}