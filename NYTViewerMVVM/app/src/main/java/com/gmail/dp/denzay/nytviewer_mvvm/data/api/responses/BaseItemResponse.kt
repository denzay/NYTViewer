package com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date


open class BaseItemResponse {

    @SerializedName("id")
    @Expose
    var id: Long? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("abstract")
    @Expose
    var shortDesc: String? = null
    @SerializedName("published_date")
    @Expose
    var publishedDate: Date? = null
    @SerializedName("media")
    @Expose
    var media: List<MediaInfoResponse>? = null

    fun getSmallImageInfo(): MediaInfoResponse.ImageInfoItem? {
        var result: MediaInfoResponse.ImageInfoItem? = null

        media?.let {
            if(it.isNotEmpty() && it[0].mediaMetadata.size >= 1)
                result = it[0].mediaMetadata[0]
        }
        return result
    }

    fun getMediumImageInfo(): MediaInfoResponse.ImageInfoItem? {
        var result: MediaInfoResponse.ImageInfoItem? = null

        media?.let {
            if(it.isNotEmpty() && it[0].mediaMetadata.size >= 2)
                result = it[0].mediaMetadata[1]
        }
        return result
    }

    fun getLargeImageInfo(): MediaInfoResponse.ImageInfoItem? {
        var result: MediaInfoResponse.ImageInfoItem? = null

        media?.let {
            if(it.isNotEmpty() && it[0].mediaMetadata.size >= 3)
                result = it[0].mediaMetadata[2]
        }
        return result
    }

    fun getFirstImageInfo(): MediaInfoResponse.ImageInfoItem? {
        var result = getLargeImageInfo()
        if (result == null) {
            result = getMediumImageInfo()
            if (result == null)
                result = getSmallImageInfo()
        }
        return result
    }

}