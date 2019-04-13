package com.gmail.dp.denzay.nytviewer_mvvm

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.NewsFragmentPageAdapter
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.MainRouter
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private var mSectionsPagerAdapter: NewsFragmentPageAdapter? = null

    @Inject
    lateinit var router: MainRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = NewsFragmentPageAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_view_favourites) {
            router.openFavourites()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
