package com.gmail.dp.denzay.nytviewer.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.gmail.dp.denzay.nytviewer.adapters.NewsItemRecyclerViewAdapter;
import com.gmail.dp.denzay.nytviewer.models.NewsContent;
import com.gmail.dp.denzay.nytviewer.view_models.NewsContentModelFactory;
import com.gmail.dp.denzay.nytviewer.view_models.NewsContentResponse;
import com.gmail.dp.denzay.nytviewer.view_models.NewsContentViewModel;

public class NewsListFragment extends Fragment {

    private static final String ARG_NEWS_FRAGMENT_TYPE = "NEWS_FRAGMENT_TYPE";
    private static final String KEY_DATA_LIST = "DATA_LIST";

    private NewsContentViewModel.NewsType mNewsType;

    private NewsItemRecyclerViewAdapter mNewsItemRecyclerViewAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    protected OnListFragmentInteractionListener mListener;
    protected NewsContent mNewsContent;
    protected RetainedDataFragment mRetainedDataFragment;

    public NewsListFragment() {
    }

    public static NewsListFragment newInstance(NewsContentViewModel.NewsType newsFragmentType) {
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
            mNewsType = NewsContentViewModel.NewsType.values()[getArguments().getInt(ARG_NEWS_FRAGMENT_TYPE)];
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
        mSwipeRefreshLayout.setOnRefreshListener(() -> doLoadNews(mNewsType, true));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        Context context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        mNewsContent = new NewsContent();
        mNewsItemRecyclerViewAdapter = new NewsItemRecyclerViewAdapter(mNewsContent, mListener);
        recyclerView.setAdapter(mNewsItemRecyclerViewAdapter);

        doLoadNews(mNewsType, savedInstanceState == null);

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

    private void doLoadNews(NewsContentViewModel.NewsType aNewsFragmentType, boolean aForceLoad) {
        mSwipeRefreshLayout.setRefreshing(true);

        NewsContentViewModel newsContentViewModel = ViewModelProviders.of(this, new NewsContentModelFactory(getActivity().getApplication(), aNewsFragmentType)).get(NewsContentViewModel.class);
        if (aForceLoad) {
            newsContentViewModel.doLoadData();
        }

        LiveData<NewsContentResponse<NewsContent>> newsContent = newsContentViewModel.getData();

        if (!newsContent.hasActiveObservers())
            newsContent.observe(this, (@Nullable NewsContentResponse<NewsContent> aNewsContentResponse) -> {
                mSwipeRefreshLayout.setRefreshing(false);
                if (aNewsContentResponse.getError() != null) {
                    Toast.makeText(NewsListFragment.this.getContext(), aNewsContentResponse.getError().getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                mNewsItemRecyclerViewAdapter.updateData(aNewsContentResponse.getData());
            });
    }

    // Метод для отдельного сохранения фрагментов с online-данными и кешированными
    protected String getRetainedDataFragmentTag() {
        return KEY_DATA_LIST;
    }

}
