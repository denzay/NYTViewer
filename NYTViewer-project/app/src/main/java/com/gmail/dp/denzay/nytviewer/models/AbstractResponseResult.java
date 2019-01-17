package com.gmail.dp.denzay.nytviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public abstract class AbstractResponseResult {
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
}
