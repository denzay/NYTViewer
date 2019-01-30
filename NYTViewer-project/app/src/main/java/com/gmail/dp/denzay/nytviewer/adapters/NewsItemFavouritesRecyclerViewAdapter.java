package com.gmail.dp.denzay.nytviewer.adapters;

import android.view.View;

import com.gmail.dp.denzay.nytviewer.models.NewsItem;
import com.gmail.dp.denzay.nytviewer.views.OnListFragmentInteractionListener;

import java.util.List;

public class NewsItemFavouritesRecyclerViewAdapter extends NewsItemRecyclerViewAdapter {

    public NewsItemFavouritesRecyclerViewAdapter(List<NewsItem> items, OnListFragmentInteractionListener listener) {
        super(items, listener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(holder.mItem.title);
        holder.mDescView.setText(holder.mItem.shortDescription);
        holder.mProgressBar.setVisibility(View.GONE);
        holder.mImageView.setImageBitmap(holder.mItem.getBitmap());

        holder.mView.setOnClickListener( (View v) -> {
            if (mListener!= null) {
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

}
