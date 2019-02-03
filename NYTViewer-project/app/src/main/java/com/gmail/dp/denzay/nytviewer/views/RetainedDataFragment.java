package com.gmail.dp.denzay.nytviewer.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.gmail.dp.denzay.nytviewer.models.NewsContent;

public class RetainedDataFragment extends Fragment {

    private NewsContent mNewsContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public NewsContent getNewsContent() {
        return mNewsContent;
    }

    public void setNewsContent(NewsContent mNewsContent) {
        this.mNewsContent = mNewsContent;
    }

}
