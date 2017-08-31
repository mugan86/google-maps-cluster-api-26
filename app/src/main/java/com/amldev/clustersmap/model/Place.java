package com.amldev.clustersmap.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by anartzmugika on 31/8/17.
 */

public class Place implements ClusterItem {

    public LatLng getmPosition() {
        return mPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private final LatLng mPosition;
    private String name;
    private String url;

    public Place(double lat, double lng, String name, String url) {
        this.name = name;
        this.url = url;
        mPosition = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return this.getmPosition();
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSnippet() {
        return null;
    }

}
