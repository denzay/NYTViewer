package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed

import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MostViewedModel(
        @SerializedName("views")
        @Expose
        private val viewsCount: Int? = null
): BaseModel()
