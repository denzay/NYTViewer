package com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MostViewedListResponse : BaseListResponse() {

    @SerializedName("results")
    @Expose
    val mostViewed: List<MostViewedResponse>? = null

}