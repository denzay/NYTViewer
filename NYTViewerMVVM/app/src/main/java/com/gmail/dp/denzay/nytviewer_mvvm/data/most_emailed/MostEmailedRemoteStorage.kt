package com.gmail.dp.denzay.nytviewer_mvvm.data.most_emailed

import com.gmail.dp.denzay.nytviewer_mvvm.Consts.API_KEY
import com.gmail.dp.denzay.nytviewer_mvvm.Consts.ARTICLES_PERIOD_DAYS
import com.gmail.dp.denzay.nytviewer_mvvm.data.api.NYTAPI
import com.gmail.dp.denzay.nytviewer_mvvm.data.common.BaseRemoteStorage
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedModel
import com.gmail.dp.denzay.nytviewer_mvvm.utils.network.NetworkErrorHandler
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class MostEmailedRemoteStorage @Inject constructor(errorHandler: NetworkErrorHandler, retrofit: Retrofit) : BaseRemoteStorage(errorHandler) {

    private val api: NYTAPI = retrofit.create(NYTAPI::class.java)

    fun getMostEmailedList(): Single<List<MostEmailedModel>> =
            api.getEmailedArticles(ARTICLES_PERIOD_DAYS, API_KEY).compose(doWithExceptionHandler())
}