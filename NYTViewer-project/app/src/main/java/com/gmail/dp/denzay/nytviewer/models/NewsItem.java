package com.gmail.dp.denzay.nytviewer.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class NewsItem implements Parcelable {
    public final long id;
    public final String url;
    public final String title;
    public final String shortDescription;
    public final String imgUrl;
    private Bitmap mBitmap;

    public NewsItem(long id, String url, String title, String shortDescription, String imgUrl) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.shortDescription = shortDescription;
        this.imgUrl = imgUrl;
        this.mBitmap = null;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeString(this.shortDescription);
        dest.writeString(this.imgUrl);
        // Вызывает краш приложения, без сообщения об ошибке
        // dest.writeParcelable(this.mBitmap, flags);

        if (mBitmap == null) return;;
        // Передаём картинку через массив, это работает
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] picArray = stream.toByteArray();

        dest.writeInt(stream.size());
        dest.writeByteArray(picArray);
    }

    protected NewsItem(Parcel in) {
        this.id = in.readLong();
        this.url = in.readString();
        this.title = in.readString();
        this.shortDescription = in.readString();
        this.imgUrl = in.readString();
        int size = in.readInt();
        if (size == 0) return;
        byte[] picArray = new byte[size];
        in.readByteArray(picArray);
        this.mBitmap = BitmapFactory.decodeByteArray(picArray, 0, size);
      //  this.mBitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<NewsItem> CREATOR = new Creator<NewsItem>() {
        @Override
        public NewsItem createFromParcel(Parcel source) {
            return new NewsItem(source);
        }

        @Override
        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap aBitmap) {
        if (aBitmap != null)
            this.mBitmap = aBitmap.copy(aBitmap.getConfig(), false);
        else
            this.mBitmap = null;
    }
}
