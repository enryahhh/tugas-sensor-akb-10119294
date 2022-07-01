package com.lingga_10119294.tugas_sensor.models;

import com.google.android.gms.maps.model.LatLng;

public class FavoriteResto {
    private LatLng coordinate;
    private String title;

    public FavoriteResto(LatLng coordinate,String title){
        this.coordinate = coordinate;
        this.title = title;
    }

    public LatLng getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(LatLng coordinate) {
        this.coordinate = coordinate;
    }

    public String getTitle() {
        return title;
    }
}
