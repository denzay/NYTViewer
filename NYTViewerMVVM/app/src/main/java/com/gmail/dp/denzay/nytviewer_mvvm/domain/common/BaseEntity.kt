package com.gmail.dp.denzay.nytviewer_mvvm.domain.common

open class BaseEntity (
    val id: Long = -1,
    val title: String = "",
    val desc: String = "",
    val url: String = "",
    val picUrl: String = ""
)