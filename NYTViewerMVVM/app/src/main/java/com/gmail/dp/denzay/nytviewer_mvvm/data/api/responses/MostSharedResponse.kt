package com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MostSharedResponse(

        @SerializedName("share_count")
        @Expose
        private val shareCount: Int? = null

) : BaseItemResponse()
