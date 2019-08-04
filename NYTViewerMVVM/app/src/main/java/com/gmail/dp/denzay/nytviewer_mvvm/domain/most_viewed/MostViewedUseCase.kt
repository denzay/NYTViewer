package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed

import com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses.MostViewedResponse
import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseUsecase
import io.reactivex.Single

interface MostViewedUseCase: BaseUsecase {

    fun getMostViewedList(): Single<List<MostViewedResponse>>
}