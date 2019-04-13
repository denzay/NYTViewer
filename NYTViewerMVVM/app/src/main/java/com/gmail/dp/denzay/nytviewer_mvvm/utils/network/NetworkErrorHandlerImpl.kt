package com.gmail.dp.denzay.nytviewer_mvvm.utils.network

import android.content.Context

import com.gmail.dp.denzay.nytviewer_mvvm.data.common.NetworkErrorHandler
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class NetworkErrorHandlerImpl @Inject constructor(context: Context): NetworkErrorHandler {

    override fun <T> handleError(response: Response<T>): Throwable {
        val code = response.code()

        return HttpException(response)
//        return when (code) {
//            400 -> generateMessageException(response.errorBody()!!.charStream())
//            401 -> AuthException()
//            500 -> IncorrectDetailsException()
//            else -> HttpException(response)
//        }
    }


//    private fun generateMessageException(message: Reader): MessageException {
//        val error = gson.fromJson(message, ErrorResponse::class.java)
//        val str = StringBuilder()
//
//        str.append(error.message?:"")
//
//        if (error.modelState?.invalid_grant?.isNotEmpty() == true)
//            error.modelState?.invalid_grant?.forEach { str.append(it + "\n") }
//
//        if (error.modelState?.singin_limit_reached?.isNotEmpty() == true)
//            error.modelState?.singin_limit_reached?.forEach { str.append(it + "\n") }
//
//        return MessageException(str.toString())
//    }

}