package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.dp.denzay.nytviewer_mvvm.R
import com.gmail.dp.denzay.nytviewer_mvvm.databinding.FragmentNewsitemListBinding
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class BaseNewsFragment: BaseFragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var binding: FragmentNewsitemListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_newsitem_list, container, false)
        return binding.root
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =null {
////    //    val binding = NewsItemListDataBinding.inflate(inflater,  container, false)
////
////       // binding.swipeContainer.setOnRefreshListener()
//////        binding.swipeContainer.setColorSchemeResources(R.color.colorPrimary)
//////
//////
//////        return binding.getRoot()
//////
//////
//////    }

}