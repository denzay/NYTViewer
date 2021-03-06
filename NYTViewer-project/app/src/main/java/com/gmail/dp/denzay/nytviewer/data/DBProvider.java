package com.gmail.dp.denzay.nytviewer.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gmail.dp.denzay.nytviewer.NYTViewerApp;

import javax.inject.Inject;

public final class DBProvider {

    @Inject
    SQLiteOpenHelper mDbHelper;
    private SQLiteDatabase mDB = null;


    @Inject
    public DBProvider() {
        NYTViewerApp.getAppComponent().inject(this);
    }

    public synchronized void connect(){
        if (!isConnected())
            mDB = mDbHelper.getWritableDatabase();
    }

    public synchronized void disconnect() {
        if (isConnected()) {
            mDB.close();
            mDB = null;
        }
    }

    public boolean isConnected() {
        return mDB != null;
    }

    public void execSQL(String aSQL) {
        mDB.execSQL(aSQL);
    }

    // Have to close cursor, after using
    public Cursor getSQL(String aSQL) {
        return mDB.rawQuery(aSQL, null);
    }

    @Nullable
    public String DBLookup(@NonNull String aTableName, @NonNull String aColumn, @NonNull String aWhere) {
        String result = null;
        String sql = "SELECT " + aColumn + " FROM " + aTableName;
        if (aWhere != "")
            sql += " WHERE " + aWhere;
        sql += " LIMIT 1";

        try (Cursor cursor = getSQL(sql)){
            if (cursor.moveToNext()) {
                result = cursor.getString(0);
            }
        }
        return result;
    }

    @Nullable
    public byte[] DBLookupBlob(@NonNull String aTableName, @NonNull String aColumn, @NonNull String aWhere) {
        byte[] result = null;
        String sql = "SELECT " + aColumn + " FROM " + aTableName;
        if (aWhere != "")
            sql += " WHERE " + aWhere;
        sql += " LIMIT 1";

        try (Cursor cursor = getSQL(sql)) {
            if (cursor.moveToNext()) {
                result = cursor.getBlob(0);
            }
        }
        return result;
    }

    public boolean DBExists(@NonNull String aTableName, @NonNull String aWhere) {
        String sql = "SELECT 1 FROM " + aTableName;
        if (aWhere != "")
            sql += " WHERE " + aWhere;
        sql += " LIMIT 1";

        try(Cursor cursor = getSQL(sql)) {
            return cursor.moveToNext();
        }
    }

    public void insertValues(@NonNull String aTableName, ContentValues aValues) {
        mDB.insert(aTableName, null, aValues);
    }

    public void updateValues(@NonNull String aTableName, ContentValues aValues, String aWhere) {
        mDB.update(aTableName, aValues, aWhere, null);
    }

    public void deleteValues(@NonNull String aTableName, @NonNull String aWhere) {
        String sql = "DELETE FROM " + aTableName;
        if (aWhere != "")
            sql += " WHERE " + aWhere;
        execSQL(sql);
    }

    public static short getShortValue(Cursor aCursor, String aFieldName) {
        return aCursor.getShort(aCursor.getColumnIndex(aFieldName));
    }

    public static int getIntValue(Cursor aCursor, String aFieldName) {
        return aCursor.getInt(aCursor.getColumnIndex(aFieldName));
    }

    public static long getLongValue(Cursor aCursor, String aFieldName) {
        return aCursor.getLong(aCursor.getColumnIndex(aFieldName));
    }

    public static float getFloatValue(Cursor aCursor, String aFieldName) {
        return aCursor.getFloat(aCursor.getColumnIndex(aFieldName));
    }

    public static double getDoubleValue(Cursor aCursor, String aFieldName) {
        return aCursor.getDouble(aCursor.getColumnIndex(aFieldName));
    }

    public static String geStringValue(Cursor aCursor, String aFieldName) {
        return aCursor.getString(aCursor.getColumnIndex(aFieldName));
    }

    public static byte[] getBlobValue(Cursor aCursor, String aFieldName) {
        return aCursor.getBlob(aCursor.getColumnIndex(aFieldName));
    }

}
