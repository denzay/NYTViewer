package com.gmail.dp.denzay.nytviewer_mvvm.data.most_shared

import com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses.MostSharedResponse
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MostSharedRepositoryImpl @Inject constructor(private val remoteStorage: MostSharedRemoteStorage): MostSharedRepository {

    override fun getMostSharedList(): Single<List<MostSharedResponse>> =
            remoteStorage.getMostSharedList()

}