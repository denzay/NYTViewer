package com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MostEmailedListResponse : BaseListResponse() {

    @SerializedName("results")
    @Expose
    val mostEmailed: List<MostEmailedResponse>? = null

}