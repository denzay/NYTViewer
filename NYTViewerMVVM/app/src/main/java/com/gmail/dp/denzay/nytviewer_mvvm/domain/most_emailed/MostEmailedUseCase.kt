package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed

import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseUsecase
import io.reactivex.Single

interface MostEmailedUseCase: BaseUsecase {

    fun getMostEmailedList(): Single<List<MostEmailedEntity>>
}