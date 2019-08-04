package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed

import com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses.MostEmailedResponse
import io.reactivex.Single

interface MostEmailedRepository {

    fun getMostEmailedList(): Single<List<MostEmailedResponse>>
}