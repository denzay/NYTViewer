package com.gmail.dp.denzay.nytviewer_mvvm.data.most_viewed

import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedModel
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedRepository
import io.reactivex.Single
import javax.inject.Inject

class MostViewedRepositoryImpl @Inject constructor(private val remoteStorage: MostViewedRemoteStorage): MostViewedRepository {

    override fun getMostViewedList(): Single<List<MostViewedModel>> =
            remoteStorage.getMostViewedList()

}