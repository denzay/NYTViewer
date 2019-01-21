package com.gmail.dp.denzay.nytviewer.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public abstract class AbstractResponseResult {
    @SerializedName("id")
    @Expose
    protected long id;
    @SerializedName("url")
    @Expose
    protected String url;
    @SerializedName("title")
    @Expose
    protected String title;
    @SerializedName("abstract")
    @Expose
    protected String shortDesc;
    @SerializedName("published_date")
    @Expose
    protected Date publishedDate;
    @SerializedName("media")
    @Expose
    protected List<MediaInfoItem> media = null;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public long getId() { return id; }

    @Nullable
    public MediaInfoItem.ImageInfoItem getSmallImageInfo() {
        MediaInfoItem.ImageInfoItem result = null;
        if (media.size() > 0)
            if(media.get(0).getMediaMetadata().size() > 0) {
                result = media.get(0).getMediaMetadata().get(0);
            }
        return result;
    }

    @Nullable
    public MediaInfoItem.ImageInfoItem getMediumImageInfo() {
        MediaInfoItem.ImageInfoItem result = null;
        if (media.size() > 0)
            if(media.get(0).getMediaMetadata().size() >= 1) {
                result = media.get(0).getMediaMetadata().get(1);
            }
        return result;
    }

    @Nullable
    public MediaInfoItem.ImageInfoItem getLargeImageInfo() {
        MediaInfoItem.ImageInfoItem result = null;
        if (media.size() > 0)
            if(media.get(0).getMediaMetadata().size() >= 2) {
                result = media.get(0).getMediaMetadata().get(2);
            }
        return result;
    }

}
