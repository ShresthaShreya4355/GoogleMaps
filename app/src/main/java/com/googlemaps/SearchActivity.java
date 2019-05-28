package com.googlemaps;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import Model.LatitudeLongtitude;

public class SearchActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private AutoCompleteTextView atvCity;
    private Button btnSearch;
    private List<LatitudeLongtitude> latitudeLongtitudes;
    Marker markerName;
    CameraUpdate center, zoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        atvCity = findViewById(R.id.atvCity);
        btnSearch = findViewById(R.id.btnSearch);

        fillArrayListAndSetAdapter();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(atvCity.getText().toString())) {
                    atvCity.setError("please enter a place name");
                    return;
                }

                int position = SearchArrayList(atvCity.getText().toString());
                if (position > -1)
                    loadMap(position);
                else
                    Toast.makeText(SearchActivity.this, "location not found:" + atvCity.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillArrayListAndSetAdapter() {
        latitudeLongtitudes = new ArrayList<>();
        latitudeLongtitudes.add(new LatitudeLongtitude(27.7059272, 85.3302047, "Softwarica college"));
        latitudeLongtitudes.add(new LatitudeLongtitude(27.7046496, 85.3304344, "Global IME Bank"));

        String[] data = new String[latitudeLongtitudes.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = latitudeLongtitudes.get(i).getMarker();

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                SearchActivity.this, android.R.layout.simple_list_item_1, data

        );

        atvCity.setAdapter(arrayAdapter);
        atvCity.setThreshold(1);

    }

    public int SearchArrayList(String name) {
        for (int i = 0; i < latitudeLongtitudes.size(); i++) {
            if (latitudeLongtitudes.get(i).getMarker().contains(name)){
            return i;
        }
    }

    return-1;
}

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            center = CameraUpdateFactory.newLatLng(new LatLng(27.7059272, 85.3302047));
            zoom = CameraUpdateFactory.zoomTo(15);
            mMap.moveCamera(center);
            mMap.animateCamera(zoom);
            mMap.getUiSettings().setZoomControlsEnabled(true);

        }

        public void loadMap(int position){
        if(markerName!=null){
          markerName.remove();
        }

        double latitude = latitudeLongtitudes.get(position).getLatitude();
        double longtitude = latitudeLongtitudes.get(position).getLongtitude();
        String marker = latitudeLongtitudes.get(position).getMarker();
        center = CameraUpdateFactory.newLatLng(new LatLng(latitude,longtitude));
        zoom = CameraUpdateFactory.zoomTo(17);
        markerName = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longtitude)).title(marker));
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }


}





