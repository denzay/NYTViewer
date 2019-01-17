package com.gmail.dp.denzay.nytviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmailedResponseResult extends AbstractResponseResult {
    @SerializedName("email_count")
    @Expose
    protected int emailCount;

    public int getEmailCount() {
        return emailCount;
    }

}
