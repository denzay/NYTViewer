package com.gmail.dp.denzay.nytviewer.views;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Toast;

import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.adapters.NYTAPI;
import com.gmail.dp.denzay.nytviewer.adapters.NYTRequestAdapter;
import com.gmail.dp.denzay.nytviewer.adapters.NewsItemRecyclerViewAdapter;
import com.gmail.dp.denzay.nytviewer.models.AbstractResponse;
import com.gmail.dp.denzay.nytviewer.models.AbstractResponseResult;
import com.gmail.dp.denzay.nytviewer.models.EmailedResponse;
import com.gmail.dp.denzay.nytviewer.models.MediaInfoItem;
import com.gmail.dp.denzay.nytviewer.models.NewsContent;
import com.gmail.dp.denzay.nytviewer.models.NewsContent.NewsItem;
import com.gmail.dp.denzay.nytviewer.models.SharedResponse;
import com.gmail.dp.denzay.nytviewer.models.ViewedResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NewsListFragment extends Fragment {

    private static final String API_KEY = "Ny86Hu9i2xN81axhZNlgms8TGAydLikN";
    private static final int ARTICLES_PERIOD_DAYS = 30;

    public enum NewsFragmentType {nftEmailed, nftShared, nftViewed};

    private static final String ARG_NEWS_FRAGMENT_TYPE = "NEWS_FRAGMENT_TYPE";
    private NewsFragmentType mFragmentType;
    private OnListFragmentInteractionListener mListener;
    private NewsItemRecyclerViewAdapter mNewsItemRecyclerViewAdapter;
    private NewsContent mNewsContent = new NewsContent();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsitem_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            doLoadNews(mFragmentType);
            mNewsItemRecyclerViewAdapter = new NewsItemRecyclerViewAdapter(mNewsContent.getItems(), mListener);
            recyclerView.setAdapter(mNewsItemRecyclerViewAdapter);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(NewsItem item);
    }

    private void doLoadNews(NewsFragmentType aNewsFragmentType) {
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

    private Callback<EmailedResponse> emailedCallback = new Callback<EmailedResponse>() {
        @Override
        public void onResponse(Call<EmailedResponse> call, Response<EmailedResponse> response) {
            AbstractResponse result = response.body();
            processSuccessResponse(result);
        }

        @Override
        public void onFailure(Call<EmailedResponse> call, Throwable t) {
            processFailResponse(t);
        }
    };

    private Callback<SharedResponse> sharedCallback = new Callback<SharedResponse>() {
        @Override
        public void onResponse(Call<SharedResponse> call, Response<SharedResponse> response) {
            AbstractResponse result = response.body();
            processSuccessResponse(result);
        }

        @Override
        public void onFailure(Call<SharedResponse> call, Throwable t) {
            processFailResponse(t);
        }
    };

    private Callback<ViewedResponse> viewedCallback = new Callback<ViewedResponse>() {
        @Override
        public void onResponse(Call<ViewedResponse> call, Response<ViewedResponse> response) {
            AbstractResponse result = response.body();
            processSuccessResponse(result);
        }

        @Override
        public void onFailure(Call<ViewedResponse> call, Throwable t) {
            processFailResponse(t);
        }
    };

    private void processSuccessResponse(AbstractResponse response) {
        List<? extends AbstractResponseResult> listResults;
        if (response instanceof EmailedResponse) {
            listResults = ((EmailedResponse) response).getResults();
        } else if (response instanceof SharedResponse) {
            listResults = ((SharedResponse) response).getResults();
        } else if (response instanceof ViewedResponse) {
            listResults = ((ViewedResponse) response).getResults();
        } else return;


        for (AbstractResponseResult item: listResults) {
            String imageUrl = null;
            MediaInfoItem.ImageInfoItem imageInfoItem = item.getLargeImageInfo();
            if (imageInfoItem != null)
                imageUrl = imageInfoItem.getUrl();
            mNewsContent.addItem(new NewsItem(item.getId(), item.getTitle(), item.getShortDesc(), imageUrl));
        }
        mNewsItemRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void processFailResponse(Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
    }
}
