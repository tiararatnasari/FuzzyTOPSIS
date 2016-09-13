package com.example.tiararatna.mykos;

import android.graphics.Camera;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Float latitude_univ, longitude_univ, latitude_kos, longitude_kos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        latitude_univ = Float.parseFloat(getIntent().getExtras().getString("latitude_univ"));
        Log.d("la_univ", ""+latitude_univ);
        longitude_univ = Float.parseFloat(getIntent().getExtras().getString("longitude_univ"));
        Log.d("lo_univ", ""+longitude_univ);
        latitude_kos = Float.parseFloat(getIntent().getExtras().getString("latitude_kos"));
        Log.d("la_kos", ""+latitude_kos);
        longitude_kos = Float.parseFloat(getIntent().getExtras().getString("longitude_kos"));
        Log.d("lo_kos", ""+longitude_kos);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng univ = new LatLng(latitude_univ, longitude_univ);
        LatLng kos = new LatLng(latitude_kos,longitude_kos);
        mMap.addMarker(new MarkerOptions().position(univ).title("Universitas"));
        mMap.addMarker(new MarkerOptions().position(kos).title("Kos"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(univ, 12.0f));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(kos, 12.0f));
    }
}
