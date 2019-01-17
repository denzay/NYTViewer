package com.gmail.dp.denzay.nytviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SharedResponse extends AbstractResponse {
    @SerializedName("results")
    @Expose
    private List<SharedResponseResult> results = null;

    public List<SharedResponseResult> getResults() {
        return results;
    }
}
