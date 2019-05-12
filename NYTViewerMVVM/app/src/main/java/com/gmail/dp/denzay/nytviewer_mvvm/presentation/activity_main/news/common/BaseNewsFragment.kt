package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gmail.dp.denzay.nytviewer_mvvm.R
import com.gmail.dp.denzay.nytviewer_mvvm.databinding.FragmentNewsitemListBinding
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_emailed.MostEmailedContract
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class BaseNewsFragment: BaseFragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var binding: FragmentNewsitemListBinding
    protected lateinit var adapter: NewsListAdapter

    lateinit var viewModel: BaseNewsContract.NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_newsitem_list, container, false)

        binding.swipeContainer.setOnRefreshListener {
            viewModel.refreshData()
        }

        adapter = NewsListAdapter()
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    protected fun subscribeToModel() {
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

        viewModel.doShowNoInternetToast.observe(this, Observer {
            Toast.makeText(requireContext(), R.string.error_no_internet, Toast.LENGTH_LONG).show()
        })
    }
}