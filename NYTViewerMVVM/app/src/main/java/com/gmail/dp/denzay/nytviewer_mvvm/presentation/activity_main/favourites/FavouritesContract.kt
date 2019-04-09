package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.favourites

import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsContract
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.base.BaseViewModel

interface FavouritesContract {

    interface ViewModel : BaseViewModel

    interface Router : BaseNewsContract.Router {
        fun openFavourites()
    }

}