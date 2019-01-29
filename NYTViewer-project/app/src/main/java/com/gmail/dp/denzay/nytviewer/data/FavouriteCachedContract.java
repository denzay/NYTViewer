package com.gmail.dp.denzay.nytviewer.data;

import android.provider.BaseColumns;

public final class FavouriteCachedContract {

    private FavouriteCachedContract() {}

    public static class FavouriteCachedEntry implements BaseColumns {
        public static final String TABLE_NAME = "cached_pages";
        public static final String COLUMN_NAME_ARTICLE_ID = "article_id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PATH = "path";
    }

}
