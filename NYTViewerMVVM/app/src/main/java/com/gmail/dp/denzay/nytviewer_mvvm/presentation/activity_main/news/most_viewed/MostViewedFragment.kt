package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_viewed

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsFragment

class MostViewedFragment : BaseNewsFragment() {

    lateinit var viewModel: MostViewedContract.ViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MostViewedViewModel::class.java)
        subscribeToModel()
    }

    private fun subscribeToModel() {
        viewModel.bound()
    }

    companion object {
        fun newInstance(): MostViewedFragment {
            return MostViewedFragment()
        }
    }
}