package com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses

import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MostViewedResponse : BaseResponse() {

    @SerializedName("results")
    @Expose
    val mostViewed: List<MostViewedModel>? = null
}