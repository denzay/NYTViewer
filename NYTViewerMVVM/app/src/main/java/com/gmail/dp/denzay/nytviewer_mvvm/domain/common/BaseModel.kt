package com.gmail.dp.denzay.nytviewer_mvvm.domain.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

open class BaseModel {

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
    var media: List<MediaInfoItem>? = null

    fun getSmallImageInfo(): MediaInfoItem.ImageInfoItem? {
        var result: MediaInfoItem.ImageInfoItem? = null

        media?.let {
            if(it.isNotEmpty() && it[0].mediaMetadata.size >= 1)
                result = it[0].mediaMetadata[0]
        }
        return result
    }

    fun getMediumImageInfo(): MediaInfoItem.ImageInfoItem? {
        var result: MediaInfoItem.ImageInfoItem? = null

        media?.let {
            if(it.isNotEmpty() && it[0].mediaMetadata.size >= 2)
                result = it[0].mediaMetadata[1]
        }
        return result
    }

    fun getLargeImageInfo(): MediaInfoItem.ImageInfoItem? {
        var result: MediaInfoItem.ImageInfoItem? = null

        media?.let {
            if(it.isNotEmpty() && it[0].mediaMetadata.size >= 3)
                result = it[0].mediaMetadata[2]
        }
        return result
    }

    fun getFirstImageInfo(): MediaInfoItem.ImageInfoItem? {
        var result = getLargeImageInfo()
        if (result == null) {
            result = getMediumImageInfo()
            if (result == null)
                result = getSmallImageInfo()
        }
        return result
    }

}