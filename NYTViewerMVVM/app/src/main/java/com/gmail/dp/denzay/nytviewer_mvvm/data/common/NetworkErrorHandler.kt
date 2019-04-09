package com.gmail.dp.denzay.nytviewer_mvvm.data.common

import retrofit2.Response

interface NetworkErrorHandler {
    fun <T> handleError(response: Response<T>): Throwable
}