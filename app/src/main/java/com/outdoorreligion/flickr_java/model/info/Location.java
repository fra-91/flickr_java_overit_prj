package com.outdoorreligion.flickr_java.model.info;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("locality")
    private Locality locality;
    @SerializedName("county")
    private County county;
    @SerializedName("region")
    private Region region;
    @SerializedName("country")
    private Country country;

    public Locality getLocality() {
        return locality;
    }

    public County getCounty() {
        return county;
    }

    public Region getRegion() {
        return region;
    }

    public Country getCountry() {
        return country;
    }
}
