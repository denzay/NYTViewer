package com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MostViewedResponse(
        @SerializedName("views")
        @Expose
        private val viewsCount: Int? = null
) : BaseItemResponse()
