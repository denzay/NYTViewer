package com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses

import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MostSharedResponse {

    @SerializedName("results")
    @Expose
    val mostShared: List<MostSharedModel>? = null

}