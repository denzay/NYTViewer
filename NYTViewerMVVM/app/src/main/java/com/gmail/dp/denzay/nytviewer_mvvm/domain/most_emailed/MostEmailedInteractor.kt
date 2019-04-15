package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed

import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseInteractor
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MostEmailedInteractor @Inject constructor(private val mostEmailedRepository: MostEmailedRepository): BaseInteractor(), MostEmailedUseCase {

    override fun getMostEmailedList(): Single<List<MostEmailedModel>> =
            mostEmailedRepository.getMostEmailedList()
}