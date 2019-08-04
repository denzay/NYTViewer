package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared

import com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses.MostSharedResponse
import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseInteractor
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MostSharedInteractor @Inject constructor(private val mostSharedRepository: MostSharedRepository): BaseInteractor(), MostSharedUseCase {

    override fun getMostSharedList(): Single<List<MostSharedResponse>> =
        mostSharedRepository.getMostSharedList()

}