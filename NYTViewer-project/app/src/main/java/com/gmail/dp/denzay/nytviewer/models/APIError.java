package com.gmail.dp.denzay.nytviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIError {

    private String ERROR_CODE_RATE_LIMIT = "policies.ratelimit.QuotaViolation";

    public boolean isRateLimitError() {
       return faultData.detail.errorCode.equals(ERROR_CODE_RATE_LIMIT);
    }

    @SerializedName("fault")
    @Expose
    private FaultData faultData;

    private static class FaultData {
        @SerializedName("faultstring")
        @Expose
        private String faultString;

        @SerializedName("detail")
        @Expose
        private Detail detail;
    }

    private static class Detail {
        @SerializedName("errorcode")
        @Expose
        private String errorCode;
    }

    public String getFaultString() {
        return faultData.faultString;
    }

    public String getErrorCode() {
        return faultData.detail.errorCode;
    }

    @Override
    public String toString() {
        return getErrorCode() + "\n" + getFaultString();
    }
}
