package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared

import io.reactivex.Single

interface MostSharedRepository {

    fun getMostSharedList(): Single<List<MostSharedModel>>
}