package com.upt.touchupt;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsClientActivity extends FragmentActivity implements OnMapReadyCallback {

    //private final LatLng coordenadasUPT = new LatLng(-18.0038755, -70.225904);

    private GoogleMap mMap;

    double Latitud;
    double Longitud;

     String Direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_client);

        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null) {
            Direccion  = extras.getString("direccion");
            Latitud  = extras.getDouble("latitud");
            Longitud = extras.getDouble("longitud");

        }else{
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng coordenadasTHIS = new LatLng(Latitud, Longitud);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenadasTHIS, 15));
        mMap.addMarker(new MarkerOptions().position(coordenadasTHIS).title(Direccion));

    }
}
