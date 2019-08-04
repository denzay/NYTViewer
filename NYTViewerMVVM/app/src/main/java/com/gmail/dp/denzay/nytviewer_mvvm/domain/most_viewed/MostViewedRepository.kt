package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed

import com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses.MostViewedResponse
import io.reactivex.Single

interface MostViewedRepository {

    fun getMostViewedList(): Single<List<MostViewedResponse>>
}