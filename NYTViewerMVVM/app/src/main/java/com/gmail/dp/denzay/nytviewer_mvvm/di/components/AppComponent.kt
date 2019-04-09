package com.gmail.dp.denzay.nytviewer_mvvm.di.components

import android.content.Context
import com.gmail.dp.denzay.nytviewer_mvvm.NYTViewerApp
import com.gmail.dp.denzay.nytviewer_mvvm.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setContext(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(app: NYTViewerApp)
}