package com.gmail.dp.denzay.nytviewer.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsContent {

    private final List<NewsItem> ITEMS = Collections.synchronizedList(new ArrayList<NewsItem>());

    public void addItem(NewsItem item) {
        ITEMS.add(item);
    }

    public List<NewsItem> getItems() {
        return ITEMS;
    }

}
