package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed

import com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses.MostViewedResponse
import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseInteractor
import io.reactivex.Single
import javax.inject.Inject

class MostViewedInteractor @Inject constructor(private val mostViewedRepository: MostViewedRepository): BaseInteractor(), MostViewedUseCase {

    override fun getMostViewedList(): Single<List<MostViewedResponse>> =
        mostViewedRepository.getMostViewedList()

}