package com.gmail.dp.denzay.nytviewer.view_models;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.adapters.NYTAPI;
import com.gmail.dp.denzay.nytviewer.adapters.NYTRequestAdapter;
import com.gmail.dp.denzay.nytviewer.models.APIError;
import com.gmail.dp.denzay.nytviewer.models.AbstractResponse;
import com.gmail.dp.denzay.nytviewer.models.AbstractResponseResult;
import com.gmail.dp.denzay.nytviewer.models.EmailedResponse;
import com.gmail.dp.denzay.nytviewer.models.MediaInfoItem;
import com.gmail.dp.denzay.nytviewer.models.NewsContent;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;
import com.gmail.dp.denzay.nytviewer.models.SharedResponse;
import com.gmail.dp.denzay.nytviewer.models.ViewedResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsContentViewModel extends AndroidViewModel {

    private static final String API_KEY = "Ny86Hu9i2xN81axhZNlgms8TGAydLikN";
    private static final int ARTICLES_PERIOD_DAYS = 30;
    public enum NewsType {ntEmailed, ntShared, ntViewed}


    private NewsType mNewsType;
    private MutableLiveData<NewsContentResponse<NewsContent>> mNewsData;

    public NewsContentViewModel(@NonNull Application application, NewsType aNewsType) {
        super(application);
        mNewsType = aNewsType;
    }


    public LiveData<NewsContentResponse<NewsContent>> getData() {
        return mNewsData;
    }

    public void doLoadData() {
        if (mNewsData == null) {
            mNewsData = new MutableLiveData<>();
        }
        loadData();
    }

    private void loadData() {
        NYTAPI nytAPI = NYTRequestAdapter.getInstance().getNYTAPI();
        switch (mNewsType) {
            case ntEmailed:
                nytAPI.getEmailedArticles(ARTICLES_PERIOD_DAYS, API_KEY).enqueue(emailedCallback);
                break;
            case ntShared:
                nytAPI.getSharedArticles(ARTICLES_PERIOD_DAYS, API_KEY).enqueue(sharedCallback);
                break;
            case ntViewed:
                nytAPI.getViewedArticles(ARTICLES_PERIOD_DAYS, API_KEY).enqueue(viewedCallback);
                break;
        }

    }

    //region request callbacks
    private Callback<EmailedResponse> emailedCallback = new Callback<EmailedResponse>() {
        @Override
        public void onResponse(Call<EmailedResponse> call, Response<EmailedResponse> response) {
            processSuccessResponse(response);
        }

        @Override
        public void onFailure(Call<EmailedResponse> call, Throwable t) {
            processFailResponse(t);
        }
    };

    private Callback<SharedResponse> sharedCallback = new Callback<SharedResponse>() {
        @Override
        public void onResponse(Call<SharedResponse> call, Response<SharedResponse> response) {
            processSuccessResponse(response);
        }

        @Override
        public void onFailure(Call<SharedResponse> call, Throwable t) {
            processFailResponse(t);
        }
    };

    private Callback<ViewedResponse> viewedCallback = new Callback<ViewedResponse>() {
        @Override
        public void onResponse(Call<ViewedResponse> call, Response<ViewedResponse> response) {
            processSuccessResponse(response);
        }

        @Override
        public void onFailure(Call<ViewedResponse> call, Throwable t) {
            processFailResponse(t);
        }
    };
    //endregion

    private void processSuccessResponse(Response<? extends AbstractResponse> aResponse) {
        if (aResponse.isSuccessful()) {
            AbstractResponse response = aResponse.body();
            NewsContent newsContent = new NewsContent();

            List<? extends AbstractResponseResult> listResults;
            if (response instanceof EmailedResponse) {
                listResults = ((EmailedResponse) response).getResults();
            } else if (response instanceof SharedResponse) {
                listResults = ((SharedResponse) response).getResults();
            } else if (response instanceof ViewedResponse) {
                listResults = ((ViewedResponse) response).getResults();
            } else return;

            for (AbstractResponseResult item : listResults) {
                String imageUrl = null;
                MediaInfoItem.ImageInfoItem imageInfoItem = item.getLargeImageInfo();
                if (imageInfoItem == null)
                    imageInfoItem = item.getMediumImageInfo();
                if (imageInfoItem == null)
                    imageInfoItem = item.getSmallImageInfo();
                if (imageInfoItem != null)
                    imageUrl = imageInfoItem.getUrl();

                newsContent.addItem(new NewsItem(item.getId(), item.getUrl(), item.getTitle(), item.getShortDesc(), imageUrl));
            }

            NewsContentResponse newsContentResponse = NewsContentResponse.success(newsContent);
            mNewsData.postValue(newsContentResponse);
        } else {
            String errorMsg = NYTRequestAdapter.processHTTPError(aResponse.code());
            if (errorMsg == null) {
                APIError error = NYTRequestAdapter.getInstance().parseError(aResponse);
                processFailResponse(error);
            } else
                processFailResponse(errorMsg);
        }
    }

    private void processFailResponse(String errorMsg) {
        Throwable t = new Throwable(errorMsg);
        processFailResponse(t);
    }

    private void processFailResponse(Throwable t) {
        NewsContentResponse newsContentResponse = NewsContentResponse.error(t);
        mNewsData.postValue(newsContentResponse);
    }

    private void processFailResponse(APIError apiError) {
        String errorMsg = apiError.isRateLimitError() ? getApplication().getResources().getString(R.string.api_error_rate_limit) : apiError.toString();
        processFailResponse(errorMsg);
    }
}
