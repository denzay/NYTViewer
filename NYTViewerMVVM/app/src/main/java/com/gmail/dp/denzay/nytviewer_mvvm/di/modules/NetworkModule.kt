package com.gmail.dp.denzay.nytviewer_mvvm.di.modules

import android.content.Context
import com.gmail.dp.denzay.nytviewer_mvvm.BuildConfig
import com.gmail.dp.denzay.nytviewer_mvvm.Consts
import com.gmail.dp.denzay.nytviewer_mvvm.data.api.NYTAPI
import com.gmail.dp.denzay.nytviewer_mvvm.data.common.NetworkErrorHandler
import com.gmail.dp.denzay.nytviewer_mvvm.utils.network.NetworkErrorHandlerImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val LOGGING = BuildConfig.DEBUG

    @Singleton
    @Provides
    fun provideNYTAPI(retrofit: Retrofit): NYTAPI = retrofit.create(NYTAPI::class.java)

    @Singleton
    @Provides
    fun provideOkHTTPClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(Consts.NETWORK_TIMEOUT_SEC, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(Consts.NETWORK_TIMEOUT_SEC, TimeUnit.SECONDS)
        clientBuilder.readTimeout(Consts.NETWORK_TIMEOUT_SEC, TimeUnit.SECONDS)

        if (LOGGING) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        builder.client(httpClient)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideNetworkErrorHandler(handler: NetworkErrorHandlerImpl, context: Context): NetworkErrorHandler = NetworkErrorHandlerImpl(context)
}