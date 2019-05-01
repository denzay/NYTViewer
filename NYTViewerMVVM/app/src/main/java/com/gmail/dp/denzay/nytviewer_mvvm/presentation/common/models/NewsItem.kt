package com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.models

import android.os.Parcel
import android.os.Parcelable

class NewsItem(
    val id: Long,
    val url: String,
    val title: String,
    val shortDescription: String,
    val imgUrl: String?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(url)
        parcel.writeString(title)
        parcel.writeString(shortDescription)
        parcel.writeString(imgUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        else if (other == null || this.javaClass != other.javaClass) return false

        val newsItem = other as NewsItem
        return id == newsItem.id &&
                url == newsItem.url &&
                title == newsItem.title &&
                shortDescription == newsItem.shortDescription &&
                imgUrl == newsItem.imgUrl
    }

    companion object CREATOR : Parcelable.Creator<NewsItem> {
        override fun createFromParcel(parcel: Parcel): NewsItem {
            return NewsItem(parcel)
        }

        override fun newArray(size: Int): Array<NewsItem?> {
            return arrayOfNulls(size)
        }
    }
}