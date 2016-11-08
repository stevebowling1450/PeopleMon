package com.stveo.stevebowling.budget.Views;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.stveo.stevebowling.budget.MainActivity;
import com.stveo.stevebowling.budget.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MapsView extends RelativeLayout implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMyLocationButtonClickListener {

    private GoogleMap mMap;
    private Context context;
    public LatLng Home;
    private double lat = 37.52917112;
    private double lng = -82.66207811;


    @Bind(R.id.map)
    MapView mapView;


    public MapsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


    @Override
    protected void onFinishInflate() {


        ButterKnife.bind(this);
        super.onFinishInflate();
        mapView.getMapAsync(this);


        mapView.onCreate(((MainActivity) getContext()).savedInstanceState);
        mapView.onResume();


    }

   // LatLng Banner = new LatLng(37.52917112, -82.66207811);


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);
        UiSettings UiSettings= mMap.getUiSettings();
        UiSettings.setZoomControlsEnabled(true);
        UiSettings.setCompassEnabled(true);

        Toast.makeText(context, "Map loaded", Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            }
        Home = new LatLng(lat, lng);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Home,10));
        mMap.setMyLocationEnabled(true);

            //LatLng paintsville = new LatLng(37.8145, -82.8071);
            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paintsville, 13));


       }




//    public void onLocationChanged(Location location) {
//        double latitude = location.getLatitude();
//        double longitude = location.getLongitude();
//        LatLng latLng = new LatLng(latitude, longitude);
//
//       mMap.addMarker(new MarkerOptions().position(latLng));
//       mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//       mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
//
//    }




    @Override
    public void onConnected(@Nullable Bundle bundle) {


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }


//    LatLng myCoordinates = new LatLng(latitude, longitude);
//    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(myCoordinates, 12);
//    mMap.animateCamera(yourLocation);



    @Override
    public boolean onMyLocationButtonClick() {
        LatLng current = new LatLng(37.52917112, -82.66207811);
        mMap.addMarker(new MarkerOptions().position(current).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        return true;
    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            mMap.clear();
            //  mLastLocation = location;
            lat = location.getLatitude();
            lng = location.getLongitude();
            Home = new LatLng(lat, lng);
            String pos = Home +"";
            Log.d("****", pos );
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Home,10));
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(loc));
        }
    };






}