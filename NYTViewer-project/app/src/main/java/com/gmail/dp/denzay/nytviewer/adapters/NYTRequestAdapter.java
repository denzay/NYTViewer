package com.gmail.dp.denzay.nytviewer.adapters;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NYTRequestAdapter {

    private static final NYTRequestAdapter mInstance = new NYTRequestAdapter();
    private static final String BASE_URL = "https://api.nytimes.com/";

    private Retrofit mRetrofit;

    public static NYTRequestAdapter getInstance() {
        return mInstance;
    }

    private NYTRequestAdapter() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public NYTAPI getNYTAPI() {
        return mRetrofit.create(NYTAPI.class);
    }

}
