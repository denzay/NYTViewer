package com.gmail.dp.denzay.nytviewer.models;

import java.util.ArrayList;
import java.util.List;

public class NewsContent {

    private final List<NewsItem> ITEMS = new ArrayList<NewsItem>();

    public void addItem(NewsItem item) {
        ITEMS.add(item);
    }

    public static class NewsItem {
        public final long id;
        public final String title;
        public final String shortDescription;
        public final String imgUrl;

        public NewsItem(long id, String title, String shortDescription, String imgUrl) {
            this.id = id;
            this.title = title;
            this.shortDescription = shortDescription;
            this.imgUrl = imgUrl;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    public List<NewsItem> getItems() {
        return ITEMS;
    }

}
