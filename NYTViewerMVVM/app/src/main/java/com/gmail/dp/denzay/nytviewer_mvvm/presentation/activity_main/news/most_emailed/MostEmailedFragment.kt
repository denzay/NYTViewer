package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_emailed

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsFragment

class MostEmailedFragment: BaseNewsFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MostEmailedViewModel::class.java)
        subscribeToModel()
    }

    companion object {
        fun newInstance(): MostEmailedFragment {
            return MostEmailedFragment()
        }
    }
}