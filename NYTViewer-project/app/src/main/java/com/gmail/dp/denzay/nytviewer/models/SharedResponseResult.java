package com.gmail.dp.denzay.nytviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class SharedResponseResult extends AbstractResponseResult {
    @SerializedName("share_count")
    @Expose
    private int shareCount;

    public int getShareCount() {
        return shareCount;
    }
}

