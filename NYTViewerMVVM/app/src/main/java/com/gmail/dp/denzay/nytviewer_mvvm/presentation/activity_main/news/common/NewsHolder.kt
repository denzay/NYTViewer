package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common

import android.support.v7.widget.RecyclerView
import com.gmail.dp.denzay.nytviewer_mvvm.databinding.ItemNewsBinding
import com.gmail.dp.denzay.nytviewer_mvvm.domain.news_item.NewsItem

class NewsHolder(private val itemViewBinding: ItemNewsBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {

    // ToDo: add onFavouritesClick
    fun bind(model: NewsItem) {
        itemViewBinding.newsItem = model
    }
}