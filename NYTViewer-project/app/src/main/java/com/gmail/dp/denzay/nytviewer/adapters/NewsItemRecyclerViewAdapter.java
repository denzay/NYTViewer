package com.gmail.dp.denzay.nytviewer.adapters;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gmail.dp.denzay.nytviewer.AsyncImageDownloader;
import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.views.NewsListFragment.OnListFragmentInteractionListener;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;


import java.util.List;

public class NewsItemRecyclerViewAdapter extends RecyclerView.Adapter<NewsItemRecyclerViewAdapter.ViewHolder> {

    protected final List<NewsItem> mValues;
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
        holder.mProgressBar.setVisibility(View.VISIBLE);

       // можно использовать либо Picasso, либо загрузчик на AsyncTask
//        Picasso.get().load(holder.mItem.imgUrl).placeholder(R.drawable.user_placeholder).error(R.drawable.user_placeholder_error).into(holder.mImageView, new com.squareup.picasso.Callback() {
//            @Override
//            public void onSuccess() {
//                holder.mProgressBar.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onError(Exception e) {
//                holder.mProgressBar.setVisibility(View.GONE);
//            }
//        });

        new AsyncImageDownloader(holder.mImageView.getContext(), holder.mCallback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, holder.mItem.imgUrl);
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
        public final ProgressBar mProgressBar;
        public final OnImageDownloadCompleteListener mCallback;
        public NewsItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.tv_Title);
            mDescView = (TextView) view.findViewById(R.id.tv_Description);
            mImageView = (ImageView) view.findViewById(R.id.iv_PreviewImage);
            mProgressBar = (ProgressBar) view.findViewById(R.id.pb_ImageLoadingProgress);
            // замыкаем коллбек на текущем вьюхолдере, для управления конкретным ImageView и ProgressBar
            mCallback = (Bitmap aBitmap) -> {
                if (aBitmap != null)
                    mImageView.setImageBitmap(aBitmap);
                mProgressBar.setVisibility(View.GONE);
            };
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }

    public interface OnImageDownloadCompleteListener {
        void OnImageDownloadComplete(Bitmap aBitmap);
    }
}
