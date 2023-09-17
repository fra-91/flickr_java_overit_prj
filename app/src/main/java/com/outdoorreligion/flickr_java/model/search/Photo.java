package com.outdoorreligion.flickr_java.model.search;

import androidx.versionedparcelable.ParcelField;
import androidx.versionedparcelable.VersionedParcelize;

import com.google.gson.annotations.SerializedName;
import com.outdoorreligion.flickr_java.model.info.PhotoInfo;

public class Photo {
    @SerializedName("id")
    private String id;

    private PhotoInfo photoInfo;

    public PhotoInfo getPhotoInfo() {
        return photoInfo;
    }

    public void setPhotoInfo(PhotoInfo photoInfo) {
        this.photoInfo = photoInfo;
    }

    public String getId() {
        return id;
    }

}
