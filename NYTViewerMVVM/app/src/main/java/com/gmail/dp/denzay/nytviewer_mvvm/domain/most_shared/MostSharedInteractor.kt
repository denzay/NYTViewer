package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared

import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseInteractor
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MostSharedInteractor @Inject constructor(private val mostSharedUseCase: MostSharedUseCase): BaseInteractor(), MostSharedUseCase {

    override fun getMostSharedList(): Single<List<MostSharedModel>> =
            mostSharedUseCase.getMostSharedList()

}