package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared

import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseEntity

class MostSharedEntity(
    id: Long,
    title: String,
    desc: String,
    url: String,
    picUrl: String
) : BaseEntity(id, title, desc, url, picUrl)
