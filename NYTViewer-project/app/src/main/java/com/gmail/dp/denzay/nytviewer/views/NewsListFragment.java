package com.gmail.dp.denzay.nytviewer.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.adapters.NYTAPI;
import com.gmail.dp.denzay.nytviewer.adapters.NYTRequestAdapter;
import com.gmail.dp.denzay.nytviewer.adapters.NewsItemRecyclerViewAdapter;
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

public class NewsListFragment extends Fragment {

    private static final String API_KEY = "Ny86Hu9i2xN81axhZNlgms8TGAydLikN";
    private static final int ARTICLES_PERIOD_DAYS = 30;

    public enum NewsFragmentType {nftEmailed, nftShared, nftViewed};

    private static final String ARG_NEWS_FRAGMENT_TYPE = "NEWS_FRAGMENT_TYPE";
    private static final String KEY_DATA_LIST = "DATA_LIST";

    private NewsFragmentType mFragmentType;

    private NewsItemRecyclerViewAdapter mNewsItemRecyclerViewAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    protected OnListFragmentInteractionListener mListener;
    protected NewsContent mNewsContent;
    protected RetainedDataFragment mRetainedDataFragment;

    public NewsListFragment() {
    }

    public static NewsListFragment newInstance(NewsFragmentType newsFragmentType) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NEWS_FRAGMENT_TYPE, newsFragmentType.ordinal());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mFragmentType = NewsFragmentType.values()[getArguments().getInt(ARG_NEWS_FRAGMENT_TYPE)];
        }

        FragmentManager fm = getFragmentManager();
        mRetainedDataFragment = (RetainedDataFragment) fm.findFragmentByTag(getRetainedDataFragmentTag());

        if (mRetainedDataFragment == null) {
            mRetainedDataFragment = new RetainedDataFragment();
            fm.beginTransaction().add(mRetainedDataFragment, getRetainedDataFragmentTag()).commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newsitem_list, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(() -> doLoadNews(mFragmentType));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        Context context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        if (savedInstanceState == null)
            mNewsContent = new NewsContent();
        else {
            mNewsContent = mRetainedDataFragment.getNewsContent();
        }

        mNewsItemRecyclerViewAdapter = new NewsItemRecyclerViewAdapter(mNewsContent, mListener);
        recyclerView.setAdapter(mNewsItemRecyclerViewAdapter);

        if (savedInstanceState == null)
            doLoadNews(mFragmentType);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mRetainedDataFragment.setNewsContent(mNewsContent);
    }

    // Метод для отдельного сохранения фрагментов с online-данными и кешированными
    protected String getRetainedDataFragmentTag() {
        return KEY_DATA_LIST;
    }

    private void doLoadNews(NewsFragmentType aNewsFragmentType) {
        mSwipeRefreshLayout.setRefreshing(true);

        NYTAPI nytAPI = NYTRequestAdapter.getInstance().getNYTAPI();
        switch (aNewsFragmentType) {
            case nftEmailed:
                nytAPI.getEmailedArticles(ARTICLES_PERIOD_DAYS, API_KEY).enqueue(emailedCallback);
                break;
            case nftShared:
                nytAPI.getSharedArticles(ARTICLES_PERIOD_DAYS, API_KEY).enqueue(sharedCallback);
                break;
            case nftViewed:
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
        mSwipeRefreshLayout.setRefreshing(false);
        if (aResponse.isSuccessful()) {
            AbstractResponse response = aResponse.body();

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
                if (imageInfoItem != null)
                    imageUrl = imageInfoItem.getUrl();
                mNewsContent.addItem(new NewsItem(item.getId(), item.getUrl(), item.getTitle(), item.getShortDesc(), imageUrl));
            }
            mNewsItemRecyclerViewAdapter.notifyDataSetChanged();
        } else {
            String errorMsg = NYTRequestAdapter.processHTTPError(aResponse.code());
            if (errorMsg == null) {
                APIError error = NYTRequestAdapter.getInstance().parseError(aResponse);
                processFailResponse(error);
            } else Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_LONG).show();
        }
    }

    private void processFailResponse(Throwable t) {
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void processFailResponse(APIError apiError) {
        mSwipeRefreshLayout.setRefreshing(false);
        String errorMsg = apiError.isRateLimitError() ? getResources().getString(R.string.api_error_rate_limit) : apiError.toString();
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_LONG).show();
    }

}
