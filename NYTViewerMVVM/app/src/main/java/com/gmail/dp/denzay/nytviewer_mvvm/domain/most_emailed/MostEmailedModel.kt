package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed

import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MostEmailedModel(

        @SerializedName("email_count")
        @Expose
        var emailCount: Int? = null

) : BaseModel()