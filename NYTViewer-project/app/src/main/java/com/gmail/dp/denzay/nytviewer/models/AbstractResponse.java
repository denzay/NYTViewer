package com.gmail.dp.denzay.nytviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class AbstractResponse {

    @SerializedName("status")
    @Expose
    protected String status;

    @SerializedName("num_results")
    @Expose
    protected int articlesCount;


    public int getArticlesCount() {
        return articlesCount;
    }

    public String getStatus() {
        return status;
    }
}
