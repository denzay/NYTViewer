package com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModelImpl: ViewModel(), BaseViewModel {

    protected val disposables = CompositeDisposable()

    override fun unbound() {
        disposables.clear()
    }
}