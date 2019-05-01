package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_emailed

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsFragment
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.NewsListAdapter

class MostEmailedFragment: BaseNewsFragment() {

    lateinit var viewModel: MostEmailedContract.ViewModel
    lateinit var adapter: NewsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MostEmailedViewModel::class.java)
        adapter = NewsListAdapter()
        binding.recyclerView.adapter = adapter
        subscribeToModel()
    }

    private fun subscribeToModel() {
        viewModel.bound()

        viewModel.isLoading.observe(this, Observer { observeValue ->
            observeValue?.let {
                binding.swipeContainer.isRefreshing = it
            }
        })

        viewModel.newsList.observe(this, Observer { observeValue ->
            observeValue?.let {
                adapter.setData(it)
            }
        })
    }

    companion object {
        fun newInstance(): MostEmailedFragment {
            return MostEmailedFragment()
        }
    }
}