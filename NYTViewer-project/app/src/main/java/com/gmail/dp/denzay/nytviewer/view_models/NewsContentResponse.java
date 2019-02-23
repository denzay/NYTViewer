package com.gmail.dp.denzay.nytviewer.view_models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class NewsContentResponse<T> {

    @Nullable
    private final T data;

    @Nullable
    private final Throwable error;

    NewsContentResponse(@Nullable T data, @Nullable Throwable error) {
        this.data = data;
        this.error = error;
    }

    @NonNull
    public static <T> NewsContentResponse<T> success(@NonNull T data) {
        return new NewsContentResponse<T>(data, null);
    }

    @NonNull
    public static <T> NewsContentResponse<T> error(@NonNull Throwable error) {
        return new NewsContentResponse<T>(null, error);
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }
}
