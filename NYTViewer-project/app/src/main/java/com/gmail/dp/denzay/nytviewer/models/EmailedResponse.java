package com.gmail.dp.denzay.nytviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class EmailedResponse extends AbstractResponse {
    @SerializedName("results")
    @Expose
    private List<EmailedResponseResult> results = null;

    public List<EmailedResponseResult> getResults() {
        return results;
    }
}
