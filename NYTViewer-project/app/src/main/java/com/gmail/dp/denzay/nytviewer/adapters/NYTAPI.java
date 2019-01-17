package com.gmail.dp.denzay.nytviewer.adapters;

import com.gmail.dp.denzay.nytviewer.models.EmailedResponse;
import com.gmail.dp.denzay.nytviewer.models.SharedResponse;
import com.gmail.dp.denzay.nytviewer.models.ViewedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NYTAPI {
    @GET("svc/mostpopular/v2/emailed/{period}.json")
    Call<EmailedResponse> getEmailedArticles(@Path("period") int period, @Query("api-key")String apiKey);
    @GET("svc/mostpopular/v2/shared/{period}.json")
    Call<SharedResponse> getSharedArticles(@Path("period") int period, @Query("api-key")String apiKey);
    @GET("svc/mostpopular/v2/viewed/{period}.json")
    Call<ViewedResponse> getViewedArticles(@Path("period") int period, @Query("api-key")String apiKey);
}
