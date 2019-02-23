package com.gmail.dp.denzay.nytviewer.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsContent {

    private final List<NewsItem> ITEMS = Collections.synchronizedList(new ArrayList<NewsItem>());

    public void addItem(NewsItem item) {
        ITEMS.add(item);
    }

    public void deleteItemById(long aID) {
        for (int i = 0; i < ITEMS.size(); i++)
            if(ITEMS.get(i).id == aID) {
                ITEMS.remove(i);
                break;
            }
    }

    public List<NewsItem> getItems() {
        return ITEMS;
    }

}
