package com.gmail.dp.denzay.nytviewer_mvvm.presentation.common

import com.gmail.dp.denzay.nytviewer_mvvm.MainActivity
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.favourites.FavouritesContract
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.favourites.FavouritesFragment
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_emailed.MostEmailedContract
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_shared.MostSharedContract
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_viewed.MostViewedContract
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.base.BaseRouter
import javax.inject.Inject

class MainRouter @Inject constructor(activity: MainActivity) : BaseRouter(activity),
        MostSharedContract.Router,
        MostEmailedContract.Router,
        MostViewedContract.Router,
        FavouritesContract.Router {

    override fun openFavourites() {
        addToRootWithBackStack(FavouritesFragment.newInstance())
    }

}