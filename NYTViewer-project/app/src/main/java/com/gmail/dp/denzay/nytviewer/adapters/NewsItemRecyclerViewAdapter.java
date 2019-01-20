package com.gmail.dp.denzay.nytviewer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.dp.denzay.nytviewer.AsyncImageDownloader;
import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.views.NewsListFragment.OnListFragmentInteractionListener;
import com.gmail.dp.denzay.nytviewer.models.NewsContent.NewsItem;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link NewsItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class NewsItemRecyclerViewAdapter extends RecyclerView.Adapter<NewsItemRecyclerViewAdapter.ViewHolder> {

    private final List<NewsItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public NewsItemRecyclerViewAdapter(List<NewsItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_newsitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(holder.mItem.title);
        holder.mDescView.setText(holder.mItem.shortDescription);

       // можно использовать либо Picasso, либо загрузчик на AsyncTask
       // Picasso.get().load(holder.mItem.imgUrl).placeholder(R.drawable.user_placeholder).error(R.drawable.user_placeholder_error).into(holder.mImageView);
        new AsyncImageDownloader(holder.mImageView).execute(holder.mItem.imgUrl);

        holder.mView.setOnClickListener( (View v) -> {
            if (null != mListener) {
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mDescView;
        public final ImageView mImageView;
        public NewsItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.tv_Title);
            mDescView = (TextView) view.findViewById(R.id.tv_Description);
            mImageView = (ImageView) view.findViewById(R.id.iv_PreviewImage);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}
