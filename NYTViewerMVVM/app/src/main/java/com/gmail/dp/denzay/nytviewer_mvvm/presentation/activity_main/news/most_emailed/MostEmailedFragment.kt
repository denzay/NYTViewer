package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_emailed

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsFragment

class MostEmailedFragment: BaseNewsFragment() {

    lateinit var viewModel: MostEmailedContract.ViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MostEmailedViewModel::class.java)
        subscribeToModel()
    }

    private fun subscribeToModel() {
        viewModel.bound()
        viewModel.isLoading.observe(this, Observer {
            
        })

    }

    companion object {
        fun newInstance(): MostEmailedFragment {
            return MostEmailedFragment()
        }
    }
}