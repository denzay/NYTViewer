package com.gmail.dp.denzay.nytviewer_mvvm.data.most_shared

import com.gmail.dp.denzay.nytviewer_mvvm.Consts
import com.gmail.dp.denzay.nytviewer_mvvm.data.api.NYTAPI
import com.gmail.dp.denzay.nytviewer_mvvm.data.common.BaseRemoteStorage
import com.gmail.dp.denzay.nytviewer_mvvm.data.common.NetworkErrorHandler
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedModel
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class MostSharedRemoteStorage @Inject constructor(errorHandler: NetworkErrorHandler, retrofit: Retrofit) : BaseRemoteStorage(errorHandler) {

    private val api: NYTAPI = retrofit.create(NYTAPI::class.java)

    fun getMostSharedList(): Single<List<MostSharedModel>> =
            api.getSharedArticles(Consts.ARTICLES_PERIOD_DAYS, Consts.API_KEY).compose(doWithExceptionHandler())
}