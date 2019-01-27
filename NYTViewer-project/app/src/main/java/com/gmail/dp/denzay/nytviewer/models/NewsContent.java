package com.gmail.dp.denzay.nytviewer.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class NewsContent {

    private final List<NewsItem> ITEMS = new ArrayList<NewsItem>();

    public void addItem(NewsItem item) {
        ITEMS.add(item);
    }

    public static class NewsItem implements Parcelable {
        public final long id;
        public final String url;
        public final String title;
        public final String shortDescription;
        public final String imgUrl;

        public NewsItem(long id, String url, String title, String shortDescription, String imgUrl) {
            this.id = id;
            this.url = url;
            this.title = title;
            this.shortDescription = shortDescription;
            this.imgUrl = imgUrl;
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
        }

        protected NewsItem(Parcel in) {
            this.id = in.readLong();
            this.url = in.readString();
            this.title = in.readString();
            this.shortDescription = in.readString();
            this.imgUrl = in.readString();
        }

        public static final Parcelable.Creator<NewsItem> CREATOR = new Parcelable.Creator<NewsItem>() {
            @Override
            public NewsItem createFromParcel(Parcel source) {
                return new NewsItem(source);
            }

            @Override
            public NewsItem[] newArray(int size) {
                return new NewsItem[size];
            }
        };
    }

    public List<NewsItem> getItems() {
        return ITEMS;
    }

}
