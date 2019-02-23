package com.gmail.dp.denzay.nytviewer.adapters;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.gmail.dp.denzay.nytviewer.AsyncImageDownloader;
import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.databinding.NewsItemDataBinding;
import com.gmail.dp.denzay.nytviewer.models.NewsContent;
import com.gmail.dp.denzay.nytviewer.models.NewsItem;
import com.gmail.dp.denzay.nytviewer.views.OnListFragmentInteractionListener;

public class NewsItemRecyclerViewAdapter extends RecyclerView.Adapter<NewsItemRecyclerViewAdapter.ViewHolder> implements android.databinding.DataBindingComponent {

    protected NewsContent mData;
    protected final OnListFragmentInteractionListener mListener;
    protected int mLastPosition = -1;

    public NewsItemRecyclerViewAdapter(NewsContent aNewsContent, OnListFragmentInteractionListener aListener) {
        mData = aNewsContent;
        mListener = aListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NewsItemDataBinding binding = NewsItemDataBinding.inflate(inflater, parent, false, this);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(mData.getItems().get(position));

        final NewsItemDataBinding itemDataBinding = holder.getBinding();
        itemDataBinding.pbImageLoadingProgress.setVisibility(View.VISIBLE);

        itemDataBinding.setNewsOnClickListener((View v) -> {
            if (mListener != null) {
                // пока айтем с картинкой не загрузился - игнорируем клик, ожидая формирования до конца модели NewsItem
                if (itemDataBinding.ivPreviewImage.getDrawable() != null) {
                    v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.anim_list_click));
                    mListener.onListFragmentInteraction(itemDataBinding.getNewsItem());
                }
            }
        });

        itemDataBinding.setNewsOnDownloadCompleteHandler( (Bitmap aBitmap) -> {
                if (aBitmap != null) {
                    itemDataBinding.ivPreviewImage.setImageBitmap(aBitmap);
                    itemDataBinding.getNewsItem().setBitmap(aBitmap);
                    itemDataBinding.pbImageLoadingProgress.setVisibility(View.GONE);
                }
            });

        Animation animation = AnimationUtils.loadAnimation(itemDataBinding.ivPreviewImage.getContext(), (position > mLastPosition) ? R.anim.anim_list_view_down_to_up : R.anim.anim_list_view_up_to_down);
        itemDataBinding.frameLayout.startAnimation(animation);
        mLastPosition = position;
    }

    @Override
    public int getItemCount() {
        return mData.getItems().size();
    }

    public void updateData(NewsContent aNewsContent) {
        mData = aNewsContent;
        notifyDataSetChanged();
    }

    @BindingAdapter({"id", "url", "onDownloadComplete"})
    public void doAsyncDownloadImage(ImageView view, long id, String url, OnImageDownloadCompleteListener aListener) {
 //       Picasso.get().load(url).into(view);
       new AsyncImageDownloader(view.getContext(), aListener).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
    }

    @Override
    public NewsItemRecyclerViewAdapter getNewsItemRecyclerViewAdapter() {
        return this;
    }

    public interface OnImageDownloadCompleteListener {
        void OnImageDownloadComplete(Bitmap aBitmap);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private NewsItemDataBinding mBinding;

        public ViewHolder(NewsItemDataBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bind(NewsItem newsItem) {
            mBinding.setNewsItem(newsItem);
            mBinding.executePendingBindings();
        }

        NewsItemDataBinding getBinding() {
            return mBinding;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mBinding.tvTitle.getText() + "'";
        }
    }
}
