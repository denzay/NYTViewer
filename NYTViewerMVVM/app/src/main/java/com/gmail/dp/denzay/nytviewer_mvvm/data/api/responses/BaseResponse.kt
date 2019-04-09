package com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse {

        @SerializedName("status")
        @Expose
        var status: String = ""

        @SerializedName("num_results")
        @Expose
        var articlesCount: Int = 0

}