package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common

import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.base.BaseViewModel


interface BaseNewsContract {

    interface NewsViewModel: BaseViewModel {
        fun showNoInternetToast()
    }

    interface Router
}