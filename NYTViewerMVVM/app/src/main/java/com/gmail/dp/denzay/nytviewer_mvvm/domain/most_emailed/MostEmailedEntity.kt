package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed

import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseEntity

class MostEmailedEntity(
    id: Long,
    title: String,
    desc: String,
    url: String,
    picUrl: String
) : BaseEntity(id, title, desc, url, picUrl)