package com.gmail.dp.denzay.nytviewer_mvvm.domain.favourites

import com.gmail.dp.denzay.nytviewer_mvvm.data.db_models.NewsItemRealm
import io.reactivex.Single

interface FavouritesRepository {
    fun getFavouritesItemList(): Single<List<NewsItemRealm>>
}