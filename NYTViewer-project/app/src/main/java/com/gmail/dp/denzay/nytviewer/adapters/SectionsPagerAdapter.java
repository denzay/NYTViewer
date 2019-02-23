package com.gmail.dp.denzay.nytviewer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gmail.dp.denzay.nytviewer.view_models.NewsContentViewModel;
import com.gmail.dp.denzay.nytviewer.views.NewsListFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGES_COUNT = 3;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return NewsListFragment.newInstance(NewsContentViewModel.NewsType.values()[position]);
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }
}
