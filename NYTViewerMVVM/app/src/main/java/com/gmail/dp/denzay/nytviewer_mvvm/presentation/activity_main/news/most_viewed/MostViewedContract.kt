package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_viewed

import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsContract

class MostViewedContract {

    interface ViewModel: BaseNewsContract.NewsViewModel

    interface Router: BaseNewsContract.Router
}