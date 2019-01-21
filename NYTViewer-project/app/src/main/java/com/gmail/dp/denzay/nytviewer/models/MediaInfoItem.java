package com.gmail.dp.denzay.nytviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MediaInfoItem {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("subtype")
    @Expose
    private String subtype;
    @SerializedName("caption")
    @Expose
    private String caption;

    @SerializedName("media-metadata")
    @Expose
    private List<ImageInfoItem> mediaMetadata;

    public List<ImageInfoItem> getMediaMetadata() {
        return mediaMetadata;
    }


    public static class ImageInfoItem {
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("height")
        @Expose
        private int height;
        @SerializedName("width")
        @Expose
        private int width;

        public String getUrl() {
            return url;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }
    }
}
