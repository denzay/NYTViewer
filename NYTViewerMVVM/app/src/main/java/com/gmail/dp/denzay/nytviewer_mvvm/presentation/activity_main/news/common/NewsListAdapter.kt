package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gmail.dp.denzay.nytviewer_mvvm.R
import com.gmail.dp.denzay.nytviewer_mvvm.databinding.ItemNewsBinding
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.models.NewsItem

class NewsListAdapter : RecyclerView.Adapter<NewsHolder>(), android.databinding.DataBindingComponent {

    private var data: MutableList<NewsItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemNewsBinding>(inflater, R.layout.item_news, parent, false)
        return NewsHolder(binding)
    }

    override fun getItemCount(): Int  {
        return data.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(values: List<NewsItem>) {
        val oldData = data
        data = arrayListOf()
        data.addAll(values)
        notifyDataSetChanged()

        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(var1: Int, var2: Int): Boolean = data[var1].id == oldData[var2].id

            override fun areContentsTheSame(var1: Int, var2: Int): Boolean = data[var1] == oldData[var2]

            override fun getOldListSize(): Int  = oldData.size

            override fun getNewListSize(): Int = data.size
        }).dispatchUpdatesTo(this)
    }
}