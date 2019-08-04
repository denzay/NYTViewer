package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed

import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseEntity

class MostViewedEntity(
    id: Long,
    title: String,
    desc: String,
    url: String,
    picUrl: String
) : BaseEntity(id, title, desc, url, picUrl)