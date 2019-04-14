package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_viewed

import android.os.Bundle
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsFragment

class MostViewedFragment : BaseNewsFragment() {

//    lateinit var viewModel: MostViewedContract.ViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//
//        val viewModel = ViewModelProviders.of(this).get(MostViewedViewModel::class.java)
//        subscribeToModel(viewModel)
    }

    private fun subscribeToModel(viewModel: MostViewedContract.ViewModel) {

    }

    companion object {
        fun newInstance(): MostViewedFragment {
            return MostViewedFragment()
        }
    }
}