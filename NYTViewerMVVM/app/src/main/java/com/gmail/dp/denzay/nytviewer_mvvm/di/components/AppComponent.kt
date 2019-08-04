package com.gmail.dp.denzay.nytviewer_mvvm.di.components

import android.app.Application
import com.gmail.dp.denzay.nytviewer_mvvm.NYTViewerApp
import com.gmail.dp.denzay.nytviewer_mvvm.di.modules.AppModule
import com.gmail.dp.denzay.nytviewer_mvvm.di.modules.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, MainActivityModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: NYTViewerApp)
}