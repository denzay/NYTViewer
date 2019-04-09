package com.gmail.dp.denzay.nytviewer_mvvm.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import javax.inject.Inject;

public class CacheStorageUtils {

    private Context mContext;

    @Inject
    public CacheStorageUtils(Context aContext) {
        mContext = aContext;
    }

    public String getExternalFolderPath() {
        String result;
        try {
            result = mContext.getExternalFilesDir(null).getAbsolutePath() + "/";
        } catch (NullPointerException e) {
            result = "";
        }
        return result;
    }

    public void deleteFile(String aFilePath) {
        if (aFilePath == null) return;
        File f = new File(aFilePath);
        if (!Environment.getExternalStorageState(f).equals(Environment.MEDIA_MOUNTED)) return;
        if (f.exists())
            f.delete();
    }
}
