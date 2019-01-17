package com.gmail.dp.denzay.nytviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class ViewedResponseResult extends AbstractResponseResult {
    @SerializedName("views")
    @Expose
    private int viewsCount;

    public int getViewsCount() {
        return viewsCount;
    }
}
