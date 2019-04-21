package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared

import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MostSharedModel(

        @SerializedName("share_count")
        @Expose
        private val shareCount: Int? = null

) : BaseModel()
