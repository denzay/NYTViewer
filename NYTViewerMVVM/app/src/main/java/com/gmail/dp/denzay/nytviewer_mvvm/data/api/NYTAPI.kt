package com.gmail.dp.denzay.nytviewer_mvvm.data.api

import com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses.MostEmailedResponse
import com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses.MostSharedResponse
import com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses.MostViewedResponse
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedModel
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedModel
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedModel

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NYTAPI {

    @GET("svc/mostpopular/v2/emailed/{period}.json")
    fun getEmailedArticles(@Path("period") period: Int, @Query("api-key") apiKey: String): Single<Response<MostEmailedResponse>>

    @GET("svc/mostpopular/v2/shared/{period}.json")
    fun getSharedArticles(@Path("period") period: Int, @Query("api-key") apiKey: String): Single<Response<MostSharedResponse>>

    @GET("svc/mostpopular/v2/viewed/{period}.json")
    fun getViewedArticles(@Path("period") period: Int, @Query("api-key") apiKey: String): Single<Response<MostViewedResponse>>
}
