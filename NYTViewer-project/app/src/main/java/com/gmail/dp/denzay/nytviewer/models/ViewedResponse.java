package com.gmail.dp.denzay.nytviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewedResponse extends AbstractResponse {
    @SerializedName("results")
    @Expose
    private List<ViewedResponseResult> results = null;

    public List<ViewedResponseResult> getResults() {
        return results;
    }
}
