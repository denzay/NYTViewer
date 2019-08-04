package com.gmail.dp.denzay.nytviewer_mvvm.data.db_models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class NewsItemRealm : RealmObject() {

    @PrimaryKey
    var id: Long = -1

    var title: String = ""

    var description: String = ""

    var bitmap: ByteArray? = null
}
