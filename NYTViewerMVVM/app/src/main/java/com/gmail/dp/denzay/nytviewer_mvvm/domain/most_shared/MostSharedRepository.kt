package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared

import com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses.MostSharedResponse
import io.reactivex.Single

interface MostSharedRepository {

    fun getMostSharedList(): Single<List<MostSharedResponse>>
}