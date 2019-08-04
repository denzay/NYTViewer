package com.gmail.dp.denzay.nytviewer_mvvm.data.local_storage

import com.gmail.dp.denzay.nytviewer_mvvm.data.db_models.NewsItemRealm
import io.reactivex.Single
import io.realm.Realm
import javax.inject.Inject

class FavouritesLocalStorage @Inject constructor(private val realm: Realm) {

    fun getNewsItemList(): Single<List<NewsItemRealm>> {
        val results = realm.where(NewsItemRealm::class.java).findAll()
        return Single.just(realm.copyFromRealm(results))
    }

}