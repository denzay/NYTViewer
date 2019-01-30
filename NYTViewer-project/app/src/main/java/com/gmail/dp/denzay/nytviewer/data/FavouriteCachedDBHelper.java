package com.gmail.dp.denzay.nytviewer.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.gmail.dp.denzay.nytviewer.data.FavouriteCachedContract.FavouriteCachedEntry;

public class FavouriteCachedDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Favorites.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FavouriteCachedEntry.TABLE_NAME + " (" +
                    FavouriteCachedEntry._ID + " INTEGER PRIMARY KEY, " +
                    FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID + " INTEGER NOT NULL, " +
                    FavouriteCachedEntry.COLUMN_NAME_TITLE + " TEXT NOT NULL," +
                    FavouriteCachedEntry.COLUMN_NAME_DESCRIPTION + " TEXT, " +
                    FavouriteCachedEntry.COLUMN_NAME_PATH + " TEXT NOT NULL, " +
                    FavouriteCachedEntry.COLUMN_NAME_PICTURE + " BLOB )";

    private static final String SQL_CREATE_INDEX_ARTICLE_ID =
        "CREATE UNIQUE INDEX IF NOT EXISTS UniqueArticleID ON " +
                FavouriteCachedEntry.TABLE_NAME + " (" + FavouriteCachedEntry.COLUMN_NAME_ARTICLE_ID + ")";

    public FavouriteCachedDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_INDEX_ARTICLE_ID);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // dummy
    }
}
