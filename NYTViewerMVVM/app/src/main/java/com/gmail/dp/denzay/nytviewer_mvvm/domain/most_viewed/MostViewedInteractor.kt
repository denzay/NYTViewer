package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed

import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseInteractor
import com.gmail.dp.denzay.nytviewer_mvvm.domain.mapper.toMostViewedEntity
import io.reactivex.Single
import javax.inject.Inject

class MostViewedInteractor @Inject constructor(private val mostViewedRepository: MostViewedRepository): BaseInteractor(), MostViewedUseCase {

    override fun getMostViewedList(): Single<List<MostViewedEntity>> =
        mostViewedRepository.getMostViewedList().map { it.map { it.toMostViewedEntity()} }

}