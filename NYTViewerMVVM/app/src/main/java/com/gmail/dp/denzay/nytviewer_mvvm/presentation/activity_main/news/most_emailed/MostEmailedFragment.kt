package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_emailed

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MostEmailedFragment: BaseNewsFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)

        val viewModel = ViewModelProviders.of(this).get(MostEmailedViewModel::class.java)
        subscribeToModel(viewModel)
    }

    private fun subscribeToModel(viewModel: MostEmailedViewModel) {

    }

    companion object {
        fun newInstance(): MostEmailedFragment {
            return MostEmailedFragment()
        }
    }
}