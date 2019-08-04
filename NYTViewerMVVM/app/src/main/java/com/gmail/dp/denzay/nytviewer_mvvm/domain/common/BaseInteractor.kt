package com.gmail.dp.denzay.nytviewer_mvvm.domain.common

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseInteractor: BaseUsecase {

    private val jobThread = Schedulers.io()
    private val observeThread = AndroidSchedulers.mainThread()

    override fun dispose() {

    }
}