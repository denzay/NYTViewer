package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.gmail.dp.denzay.nytviewer_mvvm.R
import com.gmail.dp.denzay.nytviewer_mvvm.databinding.ItemNewsBinding
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.models.NewsItem

class NewsHolder(private val itemViewBinding: ItemNewsBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {

    // ToDo: add onFavouritesClick
    fun bind(model: NewsItem) {
        itemViewBinding.newsItem = model
        Glide.with(itemViewBinding.root.context)
            .load(model.imgUrl)
            .placeholder(R.drawable.user_placeholder)
            .error(R.drawable.user_placeholder_error)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?,
                                          target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    itemViewBinding.pbImageLoadingProgress.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                             dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    itemViewBinding.pbImageLoadingProgress.visibility = View.GONE
                    return false
                }

            })
            .into(itemViewBinding.ivPreviewImage)
    }

}
