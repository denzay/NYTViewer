package com.gmail.dp.denzay.nytviewer_mvvm.data.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class APIErrorResponse {

    @SerializedName("fault")
    @Expose
    private val faultData: FaultData? = null

    private class FaultData {
        @SerializedName("faultstring")
        @Expose
        val faultString: String? = null

        @SerializedName("detail")
        @Expose
        val detail: Detail? = null
    }

    private class Detail {
        @SerializedName("errorcode")
        @Expose
        val errorCode: String? = null
    }


    private fun getFaultString(): String? {
        return faultData?.faultString
    }

    private fun getErrorCode(): String? {
        return faultData?.detail?.errorCode
    }

    fun isRateLimitError(): Boolean {
        return faultData?.detail?.errorCode == ERROR_CODE_RATE_LIMIT
    }

    override fun toString(): String {
        return getErrorCode() + "\n" + getFaultString()
    }

    companion object {
        private const val ERROR_CODE_RATE_LIMIT = "policies.ratelimit.QuotaViolation"
    }
}