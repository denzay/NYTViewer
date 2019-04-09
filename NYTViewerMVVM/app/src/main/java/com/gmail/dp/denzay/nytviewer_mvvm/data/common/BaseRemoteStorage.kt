package com.gmail.dp.denzay.nytviewer_mvvm.data.common

import com.gmail.dp.denzay.nytviewer_mvvm.utils.network.NetworkErrorHandler
import io.reactivex.Single
import io.reactivex.SingleTransformer
import retrofit2.Response

abstract class BaseRemoteStorage(private val errorHandler: NetworkErrorHandler) {

    protected fun <T> doWithExceptionHandler(): SingleTransformer<Response<T>, T> {
        return SingleTransformer { single ->
            return@SingleTransformer single.flatMap {
                if (!it.isSuccessful) {
                    Single.error(errorHandler.handleError(it))
                } else {
                    Single.just(it.body())
                }
            }
        }
    }
}