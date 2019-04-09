package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed

import io.reactivex.Single

interface MostEmailedRepository {

    fun getMostEmailedList(): Single<List<MostEmailedModel>>
}