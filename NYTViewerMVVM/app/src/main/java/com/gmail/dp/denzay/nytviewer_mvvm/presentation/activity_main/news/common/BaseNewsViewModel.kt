package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.base.BaseViewModelImpl
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.models.NewsItem
import java.net.UnknownHostException

abstract class BaseNewsViewModel : BaseViewModelImpl(), BaseNewsContract.NewsViewModel {

    override val newsList = MutableLiveData<MutableList<NewsItem>>()
    override val isLoading = MutableLiveData<Boolean>().apply { value = false }
    override val doShowNoInternetToast = MutableLiveData<Unit>()

    protected abstract fun doLoadData()

    override fun bound() {
        doLoadData()
    }

    protected fun processResponseError(error: Throwable) {
        Log.e("NYT", error.message)
        if (error is UnknownHostException)
            doShowNoInternetToast.postValue(null)
    }

    override fun refreshData() {
        doLoadData()
    }

}