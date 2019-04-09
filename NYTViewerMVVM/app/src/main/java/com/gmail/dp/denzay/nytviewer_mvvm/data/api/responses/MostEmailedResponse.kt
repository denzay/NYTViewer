package com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses

import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MostEmailedResponse: BaseResponse() {

    @SerializedName("results")
    @Expose
    val mostEmailed: List<MostEmailedModel>? = null

}