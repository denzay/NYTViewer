package com.gmail.dp.denzay.nytviewer_mvvm.data.most_viewed

import com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses.MostViewedResponse
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MostViewedRepositoryImpl @Inject constructor(private val remoteStorage: MostViewedRemoteStorage): MostViewedRepository {

    override fun getMostViewedList(): Single<List<MostViewedResponse>> =
            remoteStorage.getMostViewedList()

}