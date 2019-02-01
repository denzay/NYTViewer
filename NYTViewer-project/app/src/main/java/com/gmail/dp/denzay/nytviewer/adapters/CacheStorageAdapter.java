package com.gmail.dp.denzay.nytviewer.adapters;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class CacheStorageAdapter {

    public static String getExternalFolderPath(Context aContext) {
        return aContext.getExternalFilesDir(null).getAbsolutePath() + "/";
    }
    public static void deleteFile(String aFilePath) {
        if (aFilePath == null) return;
        File f = new File(aFilePath);
        if (!Environment.getExternalStorageState(f).equals(Environment.MEDIA_MOUNTED)) return;
        if (f.exists())
            f.delete();
    }
}
