package yclo.ntoufoodmap;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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


        SharedPreferences prefs = getSharedPreferences("Store", Context.MODE_PRIVATE);
        int indexOfList_prefs = prefs.getInt("IndexOfList_PREFS", -1);
        String addressString = RecommendActivity.address.get(indexOfList_prefs);
        Log.v("zz", addressString);
        double latitude = 0;
        double longitude = 0;
        try {
            String response = ConnectAPI.sendPost("API/getGPS.php", "address=" + addressString);
            Mapdata userData = new Gson().fromJson(response, Mapdata.class);
            latitude = userData.getLat();
            longitude = userData.getLng();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title(addressString));

        mMap.getUiSettings().setZoomControlsEnabled(true);  // 右下角的放大縮小功能
        mMap.getUiSettings().setCompassEnabled(true);       // 左上角的指南針，要兩指旋轉才會出現
        mMap.getUiSettings().setMapToolbarEnabled(true);    // 右下角的導覽及開啟 Google Map功能

        Log.d("TAG", "最高放大層級：" + mMap.getMaxZoomLevel());
        Log.d("TAG", "最低放大層級：" + mMap.getMinZoomLevel());

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));     // 放大地圖到 16 倍大
    }

    class Mapdata {
        double lat;
        double lng;

        public Mapdata() {
        }

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }

    }
}
