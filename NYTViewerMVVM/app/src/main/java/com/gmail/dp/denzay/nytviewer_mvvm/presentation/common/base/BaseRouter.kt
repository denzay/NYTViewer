package com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.gmail.dp.denzay.nytviewer_mvvm.R
import javax.inject.Inject

open class BaseRouter @Inject constructor(protected val activity: AppCompatActivity) {

    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    protected fun addToRootWithBackStack(fragment: Fragment) {
        fragmentManager
                .beginTransaction()
                .add(R.id.container, fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
                .commitAllowingStateLoss()
    }

}