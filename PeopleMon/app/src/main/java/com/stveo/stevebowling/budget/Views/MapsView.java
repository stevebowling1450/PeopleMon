package com.stveo.stevebowling.budget.Views;

import android.Manifest;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.stveo.stevebowling.budget.MainActivity;
import com.stveo.stevebowling.budget.Models.User;
import com.stveo.stevebowling.budget.Network.RestClient;
import com.stveo.stevebowling.budget.PeopleMonApplication;
import com.stveo.stevebowling.budget.R;
import com.stveo.stevebowling.budget.Stages.CaughtStage;

import java.util.Timer;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;
import flow.History;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsView extends RelativeLayout implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMyLocationButtonClickListener {

    private GoogleMap mMap;
    private Context context;
    public LatLng Home;
    private double lat;
    private double lng;
    public String otherUserId;
    public String name;
    public String id;
    public String otherUserName;

    Timer timer = new Timer();
    Handler handler = new Handler() {
        @Override
        public void publish(LogRecord logRecord) {

        }

        @Override
        public void flush() {

        }

        @Override
        public void close() throws SecurityException {

        }
    };


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
        UiSettings UiSettings = mMap.getUiSettings();



        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
//
        mMap.setMyLocationEnabled(true);



    }
//    final Runnable r = new Runnable() {
//        @Override
//        public void run() {
//            setCheckin();
//            mapView.postDelayed(r, 5000);
//        }
//
//        };


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
        if (marker.getId().contains("")){
            Toast.makeText(context, "YOU CLICKED", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "YOU CLICKED", Toast.LENGTH_SHORT).show();
            caughtUser();
        }
        return false;

    }


    @Override
    public boolean onMyLocationButtonClick() {
        LatLng current = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(current).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        return true;
    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                mMap.clear();
            lat = location.getLatitude();
            lng = location.getLongitude();
            Home = new LatLng(lat, lng);
            String pos = Home + "";
            Log.d("****", pos);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Home, 18));
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.superman))
                    .snippet("Home")
                    .draggable(true)
                    .position(Home));


            final Circle circle = mMap.addCircle(new CircleOptions().center(Home)
                    .strokeColor(Color.GREEN).radius(100));

            ValueAnimator vAnimator = new ValueAnimator();
            vAnimator.setRepeatCount(ValueAnimator.INFINITE);
            vAnimator.setRepeatMode(ValueAnimator.RESTART);  /* PULSE */
            vAnimator.setIntValues(0, 100);
            vAnimator.setDuration(1000);
            vAnimator.setEvaluator(new IntEvaluator());
            vAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            vAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float animatedFraction = valueAnimator.getAnimatedFraction();
                    //Log.e("", "" + animatedFraction);
                    circle.setRadius(animatedFraction * 100);
                }
            });
            vAnimator.start();
               setCheckin();
                viewNearby();


        }

    };

    public void setCheckin() {

        User checkin = new User(lat, lng);
        RestClient restClient = new RestClient();
        restClient.getApiSevrice().checkin(checkin).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                   // Toast.makeText(context, "Checked In ", Toast.LENGTH_SHORT).show();
                } else {
                   // Toast.makeText(context, "CheckIn Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });


    }


    public void viewNearby() {
        RestClient restClient = new RestClient();
        restClient.getApiSevrice().findNearby(100).enqueue(new Callback<User[]>() {
            @Override
            public void onResponse(Call<User[]> call, Response<User[]> response) {
                if (response.isSuccessful()) {
                    for (User user : response.body()) {
                        lat = user.getLatitude();
                        lng = user.getLongitude();
                        otherUserId =user.getUserId();
                        otherUserName = user.getUserName();
                        LatLng userpos = new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions()
                                .title(user.getUserName())
                                .snippet(otherUserId)
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.person))
                                .position(userpos));

                        //Log.d("@@@@@  ", otherUserName);
                    }
                } else {
                    Toast.makeText(context, "view Nearby Failed: " + response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User[]> call, Throwable t) {
                Toast.makeText(context, "view Nearby Failed", Toast.LENGTH_SHORT).show();

            }
        });


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
              otherUserName = marker.getTitle();
                id = marker.getSnippet();

                caughtUser();
                Toast.makeText(context, "You Caught "+ otherUserName, Toast.LENGTH_SHORT).show();
                return false;
            }
        });



    }

    public void caughtUser(){
       User caughtUserId = new User(id,100);
        RestClient restClient= new RestClient();
        restClient.getApiSevrice().catchUser(caughtUserId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
//                if (response.isSuccessful()) {
                    Toast.makeText(context, "Caught" + otherUserId, Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(context, "Not Caught", Toast.LENGTH_SHORT).show();
//                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {


            }
        });
    }
    @OnClick(R.id.caught_button)
    public void viewCaught() {
        Flow flow = PeopleMonApplication.getMainFlow();
        History newHistory = flow.getHistory().buildUpon()
                .push(new CaughtStage())
                .build();
        flow.setHistory(newHistory, Flow.Direction.FORWARD);
    }

}


