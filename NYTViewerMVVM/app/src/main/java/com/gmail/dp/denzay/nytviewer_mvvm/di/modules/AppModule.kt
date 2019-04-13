package com.gmail.dp.denzay.nytviewer_mvvm.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, UseCaseModule::class, RepositoriesModule::class, NetworkModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: Application): Context = app
}