package com.lingga_10119294.tugas_sensor.views.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.lingga_10119294.tugas_sensor.R;
import com.lingga_10119294.tugas_sensor.models.FavoriteResto;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {
    private SupportMapFragment mapFragment;
    private FusedLocationProviderClient client;
    private List<FavoriteResto> fvr = new ArrayList<>();

    private final LatLng BASO_MAS_TONI = new LatLng(-6.973609380832971, 107.61827310265045);
    private final LatLng MIXUE_BJGSG = new LatLng(-6.978719753426812, 107.63443491239366);
    private final LatLng BUBUR_PASIGARAN = new LatLng(-6.984219805829166, 107.62222012404204);
    private final LatLng BASO_MAS_TRI = new LatLng(-6.9830590389147815, 107.6116844073088);
    private final LatLng TEPUS_COFFE = new LatLng(-6.972912429903476, 107.61788544513107);

    private Location location;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
                LatLng lokasi = new LatLng(location.getLatitude(),location.getLongitude());
                MarkerOptions options = new MarkerOptions().position(lokasi).title("Lokasi Saat Ini");
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasi,13.7f));
                googleMap.addMarker(options);
                showMarkerResto(googleMap);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        fvr.add(new FavoriteResto(BASO_MAS_TONI,"Mie Bakso Pak Toni tea"));
        fvr.add(new FavoriteResto(BUBUR_PASIGARAN,"BUBUR AYAM PASIGARAN"));
        fvr.add(new FavoriteResto(MIXUE_BJGSG,"Mixue Bojongsoang"));
        fvr.add(new FavoriteResto(BASO_MAS_TRI,"Mie Ayam Bakso Mas Tri"));
        fvr.add(new FavoriteResto(TEPUS_COFFE,"Tepus Coffee"));

        mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            getCurrentLocation();
//            mapFragment.getMapAsync(callback);
        }

    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location2) {
                if(location2 != null){
                    location = location2;
                    mapFragment.getMapAsync(callback);
                }
            }
        });
    }

    private void showMarkerResto(GoogleMap map){
        for (int i = 0; i < fvr.size(); i++) {

            // below line is use to add marker to each location of our array list.
            map.addMarker(
                    new MarkerOptions()
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                            .position(fvr.get(i).getCoordinate()).title(fvr.get(i).getTitle()));

            // below lin is use to zoom our camera on map.
//            map.animateCamera(CameraUpdateFactory.zoomTo(20));

            // below line is use to move our camera to the specific location.
//            map.moveCamera(CameraUpdateFactory.newLatLng(fvr.get(i).getCoordinate()));
        }
    }

}