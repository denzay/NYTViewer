package com.gmail.dp.denzay.nytviewer.models;

import android.graphics.Bitmap;

public class NewsItemFavourite extends NewsItem {

    public Bitmap mBitmap;

    private NewsItemFavourite(long id, String url, String title, String shortDescription, String imgUrl) {
        super(id, url, title, shortDescription, imgUrl);
    }
    public NewsItemFavourite(long id, String url, String title, String shortDescription, Bitmap aBitmap) {
        this(id, url, title, shortDescription, "");
        mBitmap = aBitmap;
    }
}
