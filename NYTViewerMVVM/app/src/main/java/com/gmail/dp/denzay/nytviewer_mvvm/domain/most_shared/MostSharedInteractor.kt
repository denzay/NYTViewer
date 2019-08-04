package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared

import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseInteractor
import com.gmail.dp.denzay.nytviewer_mvvm.domain.mapper.toMostSharedEntity
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MostSharedInteractor @Inject constructor(private val mostSharedRepository: MostSharedRepository): BaseInteractor(), MostSharedUseCase {

    override fun getMostSharedList(): Single<List<MostSharedEntity>> =
        mostSharedRepository.getMostSharedList().map { it.map { it.toMostSharedEntity() } }

}