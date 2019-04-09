package com.gmail.dp.denzay.nytviewer_mvvm.data.most_emailed

import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedModel
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedRepository
import io.reactivex.Single
import javax.inject.Inject

class MostEmailedRepositoryImpl @Inject constructor(private val remoteStorage: MostEmailedRemoteStorage): MostEmailedRepository {

    override fun getMostEmailedList(): Single<List<MostEmailedModel>> =
            remoteStorage.getMostEmailedList()
}