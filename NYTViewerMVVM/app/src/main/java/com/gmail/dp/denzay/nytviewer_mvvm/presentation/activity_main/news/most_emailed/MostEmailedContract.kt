package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_emailed

import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsContract

interface MostEmailedContract {

    interface ViewModel: BaseNewsContract.NewsViewModel

    interface Router: BaseNewsContract.Router
}