package com.gmail.dp.denzay.nytviewer_mvvm.domain.mapper

import com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses.MostEmailedResponse
import com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses.MostSharedResponse
import com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses.MostViewedResponse
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedEntity
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedEntity
import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedEntity


fun MostEmailedResponse.toMostEmailedEntity(): MostEmailedEntity {
    return MostEmailedEntity(
        this.id ?: -1, this.title ?: "",
        this.shortDesc ?: "", this.url ?: "", this.getFirstImageInfo()?.url ?: ""
    )
}

fun MostSharedResponse.toMostSharedEntity(): MostSharedEntity {
    return MostSharedEntity(
        this.id ?: -1, this.title ?: "",
        this.shortDesc ?: "", this.url ?: "", this.getFirstImageInfo()?.url ?: ""
    )
}

fun MostViewedResponse.toMostViewedEntity(): MostViewedEntity {
    return MostViewedEntity(
        this.id ?: -1, this.title ?: "",
        this.shortDesc ?: "", this.url ?: "", this.getFirstImageInfo()?.url ?: ""
    )
}