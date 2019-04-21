package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_shared

import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsContract

interface MostSharedContract {

    interface ViewModel: BaseNewsContract.NewsViewModel

    interface Router: BaseNewsContract.Router
}