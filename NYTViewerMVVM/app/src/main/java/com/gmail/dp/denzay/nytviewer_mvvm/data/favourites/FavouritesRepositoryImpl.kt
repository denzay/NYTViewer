package com.gmail.dp.denzay.nytviewer_mvvm.data.favourites

import com.gmail.dp.denzay.nytviewer_mvvm.data.db_models.NewsItemRealm
import com.gmail.dp.denzay.nytviewer_mvvm.domain.favourites.FavouritesRepository
import io.reactivex.Single
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor (

    private val favouritesLocalStorage: FavouritesLocalStorage): FavouritesRepository {

    override fun getFavouritesItemList(): Single<List<NewsItemRealm>> = favouritesLocalStorage.getFavouritesItemList()

}