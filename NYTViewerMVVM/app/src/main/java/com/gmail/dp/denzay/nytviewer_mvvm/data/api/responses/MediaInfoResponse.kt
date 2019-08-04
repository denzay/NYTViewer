package com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MediaInfoResponse {
    @SerializedName("type")
    @Expose
    private val type: String? = null
    @SerializedName("subtype")
    @Expose
    private val subtype: String? = null
    @SerializedName("caption")
    @Expose
    private val caption: String? = null

    @SerializedName("media-metadata")
    @Expose
    val mediaMetadata: List<ImageInfoItem> = arrayListOf()


    class ImageInfoItem {
        @SerializedName("url")
        @Expose
        val url: String? = null
        @SerializedName("height")
        @Expose
        val height: Int = 0
        @SerializedName("width")
        @Expose
        val width: Int = 0
    }
}
