package com.gmail.dp.denzay.nytviewer.adapters;

import android.view.View;

import com.gmail.dp.denzay.nytviewer.models.NewsItem;
import com.gmail.dp.denzay.nytviewer.views.NewsListFragment;

import java.util.List;

public class NewsItemFavouritesRecyclerViewAdapter extends NewsItemRecyclerViewAdapter {

    private NewsItemFavouritesRecyclerViewAdapter(List<NewsItem> items, NewsListFragment.OnListFragmentInteractionListener listener) {
        super(items, listener);
    }

    public NewsItemFavouritesRecyclerViewAdapter(List<NewsItem> items) {
        this(items, null);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(holder.mItem.title);
        holder.mDescView.setText(holder.mItem.shortDescription);
        holder.mProgressBar.setVisibility(View.VISIBLE);
    }

}
