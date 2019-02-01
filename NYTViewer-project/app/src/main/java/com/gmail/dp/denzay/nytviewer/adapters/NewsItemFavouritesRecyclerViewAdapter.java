package com.gmail.dp.denzay.nytviewer.adapters;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.gmail.dp.denzay.nytviewer.AsyncDBImageDownloader;
import com.gmail.dp.denzay.nytviewer.R;
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
        holder.mProgressBar.setVisibility(View.VISIBLE);

        new AsyncDBImageDownloader(holder.mImageView.getContext(), holder.mCallback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, String.valueOf(holder.mItem.id));
        holder.mView.setOnClickListener( (View v) -> {
            if (mListener!= null) {
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    public NewsItem getItem(int aIndex) {
        return mValues.get(aIndex);
    }

    public void removeItem(int aIndex) {
        mValues.remove(aIndex);
    }
}
