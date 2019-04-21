package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_emailed.MostEmailedFragment
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_shared.MostSharedFragment
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_viewed.MostViewedFragment

class NewsFragmentPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(index: Int): Fragment {
        return when(index) {
            PAGE_MOST_EMAILED -> MostEmailedFragment.newInstance()
            PAGE_MOST_SHARED -> MostSharedFragment.newInstance()
            PAGE_MOST_VIEWED -> MostViewedFragment.newInstance()
            else -> throw IllegalArgumentException("Invalid fragment type: $index")
        }
    }

    override fun getCount(): Int = PAGES_COUNT

    companion object {
        const val PAGES_COUNT = 3
        const val PAGE_MOST_EMAILED = 0
        const val PAGE_MOST_SHARED = 1
        const val PAGE_MOST_VIEWED = 2
    }
}