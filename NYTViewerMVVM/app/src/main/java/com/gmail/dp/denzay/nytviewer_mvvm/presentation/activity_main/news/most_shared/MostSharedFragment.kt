package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_shared

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsFragment

class MostSharedFragment : BaseNewsFragment() {

    lateinit var viewModel: MostSharedContract.ViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MostSharedViewModel::class.java)
        subscribeToModel()
    }

    private fun subscribeToModel() {
        viewModel.bound()
    }

    companion object {
        fun newInstance(): MostSharedFragment {
            return MostSharedFragment()
        }
    }
}