package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed

import io.reactivex.Single

interface MostViewedRepository {

    fun getMostViewedList(): Single<List<MostViewedModel>>
}