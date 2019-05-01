package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common

import android.arch.lifecycle.LiveData
import com.gmail.dp.denzay.nytviewer_mvvm.domain.news_item.NewsItem
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.base.BaseViewModel


interface BaseNewsContract {

    interface NewsViewModel: BaseViewModel {
        fun showNoInternetToast()
        val isLoading: LiveData<Boolean>
        val newsList: LiveData<MutableList<NewsItem>>
    }

    interface Router
}