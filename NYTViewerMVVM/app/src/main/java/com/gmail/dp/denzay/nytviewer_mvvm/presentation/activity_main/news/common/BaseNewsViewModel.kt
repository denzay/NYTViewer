package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.gmail.dp.denzay.nytviewer_mvvm.domain.news_item.NewsItem
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.base.BaseViewModelImpl

abstract class BaseNewsViewModel : BaseViewModelImpl(), BaseNewsContract.NewsViewModel {

    override val newsList = MutableLiveData<MutableList<NewsItem>>()
    override val isLoading = MutableLiveData<Boolean>().apply { value = false }

    protected abstract fun doLoadData()

    override fun bound() {

        doLoadData()
    }

    override fun showNoInternetToast() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    protected fun processResponseError(error: Throwable) {
        Log.e("NYT", error.message)
    }
}