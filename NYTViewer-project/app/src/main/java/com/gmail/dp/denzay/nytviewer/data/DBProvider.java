package com.gmail.dp.denzay.nytviewer.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class DBProvider {

    private FavouriteCachedDBHelper mDbHelper;
    private SQLiteDatabase mDB = null;

    private static DBProvider mInstance = null;

    public synchronized static DBProvider getInstance(Context aContext) {
        if (mInstance == null)
            mInstance = new DBProvider(aContext);
        return mInstance;
    }

    private DBProvider(Context aContext) {
        mDbHelper = new FavouriteCachedDBHelper(aContext);
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

        Cursor cursor = getSQL(sql);
        try {
            if (cursor.moveToNext()) {
                result = cursor.getString(0);
            }
        } finally {
            cursor.close();
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

        Cursor cursor = getSQL(sql);
        try {
            if (cursor.moveToNext()) {
                result = cursor.getBlob(0);
            }
        } finally {
            cursor.close();
        }
        return result;
    }

    public boolean DBExists(@NonNull String aTableName, @NonNull String aColumn, @NonNull String aWhere) {
        return DBLookup(aTableName, aColumn, aWhere) != null;
    }

    public void insertValues(@NonNull String aTableName, ContentValues aValues) {
        mDB.insert(aTableName, null, aValues);
    }

    public void updateValues(@NonNull String aTableName, @NonNull String aNewValues, @NonNull String aWhere) {
        String sql = "UPDATE " + aTableName + " SET " + aNewValues;
        if (aWhere != "")
            sql += " WHERE " + aWhere;
        execSQL(sql);
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
