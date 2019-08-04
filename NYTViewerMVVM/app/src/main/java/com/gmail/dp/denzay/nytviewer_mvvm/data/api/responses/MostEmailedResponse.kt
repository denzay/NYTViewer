package com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MostEmailedResponse(

        @SerializedName("email_count")
        @Expose
        var emailCount: Int? = null

) : BaseItemResponse()