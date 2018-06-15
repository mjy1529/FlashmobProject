package com.flashmob_team.usr.flashmob_project.Flashmob;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flashmob_team.usr.flashmob_project.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


@SuppressLint("ValidFragment")
public class DetailsFragment extends Fragment implements OnMapReadyCallback {

    Context mContext;
    private GoogleMap googleMap;
    private MapView map = null;
    private boolean mapsSupported = true;

    String date;
    Double placeLatitude;
    Double placeLongitude;
    CharSequence placeName;
    CharSequence placeAddress;

    TextView showDate;
    TextView showMemo;

    public DetailsFragment(Context context) {


        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, null);
        map = (MapView) view.findViewById(R.id.map);
        showDate = (TextView)view.findViewById(R.id.showDate);
        showMemo = (TextView)view.findViewById(R.id.showMemo);

        placeLatitude = 37.5609739;
        placeLongitude = 126.99352870000007;
        placeName = "충무로역";
        placeAddress = "대한민국 서울특별시 필동2가";


  //      Bundle bundle = this.getArguments();
//        showDate.setText(bundle.getString("date"));
//
//        Bundle bundle = this.getArguments();
////        if(bundle != null) {
//            placeLatitude = bundle.getDouble("latitude");
//            placeLongitude = bundle.getDouble("longitude");
//            date = bundle.getString("date");
//            placeName = bundle.getCharSequence("placeName");
//            placeAddress = bundle.getCharSequence("placeAddress");
//            showDate.setText(bundle.getString("date"));
//            showMemo.setText(bundle.getString("memo"));
//        }

        map.getMapAsync(this);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);






        if(map != null) {
            map.onCreate(savedInstanceState);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng tmp = new LatLng(placeLatitude, placeLongitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(tmp)
                .title(placeName.toString())
                .snippet(placeAddress.toString());
        Marker marker = googleMap.addMarker(markerOptions);
        marker.showInfoWindow();

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(tmp));
        googleMap.setMinZoomPreference(15.0f);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        map.onSaveInstanceState(outState);

    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }
}
