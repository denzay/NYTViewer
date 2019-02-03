package com.gmail.dp.denzay.nytviewer.adapters;

import android.support.annotation.Nullable;

import com.gmail.dp.denzay.nytviewer.models.APIError;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
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

    public APIError parseError (Response<?> response) {
        Converter<ResponseBody, APIError> converter = mRetrofit.responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }

    // частичный анализ HTTP-кодов ошибок, которые могут возникнуть
    @Nullable
    public static String processHTTPError(final int aErrorCode) {
        // 407 - ошибка лимита запросов, её обрабатываем через parseError

        switch (aErrorCode) {
            case 400:
                return "Bad Request";
            case 401:
                return "Unauthorized";
            case 403:
                return "Forbidden";
            case 404:
                return "Not found";
            case 500:
                return "Internal Server Error";
            case 501:
                return "Not Implemented";
            case 502:
                return "Bad Gateway";
            case 503:
                return "Service Unavailable";
            case 504:
                return "Gateway Timeout";
            default:
                return null;
        }
    }

}
