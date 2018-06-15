package com.flashmob_team.usr.flashmob_project.SignUp;

import com.bumptech.glide.Glide;
import com.flashmob_team.usr.flashmob_project.Application.ApplicationController;
import com.flashmob_team.usr.flashmob_project.Network.NetworkService;
import com.flashmob_team.usr.flashmob_project.R;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignLocationActivity extends AppCompatActivity {
    Button sign_loca_left_button;
    public static Button sign_loca_right_button;

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private LocationAdapter locationAdapter;

    private NetworkService service;

    private ArrayList<LocationData> locationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_location);

        Log.d("Log", "4444");

        service = ApplicationController.getInstance().getNetworkService();

        sign_loca_left_button = (Button) findViewById(R.id.sign_loca_left_button);
        sign_loca_right_button = (Button) findViewById(R.id.sign_loca_right_button);

        recyclerView = (RecyclerView) findViewById(R.id.sign_loca_recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        locationAdapter = new LocationAdapter(getApplicationContext(), locationData, clickEvent);
        recyclerView.setAdapter(locationAdapter);

        Networking();
    }

    public View.OnClickListener clickEvent = new View.OnClickListener() {
        public void onClick(View v) {
            int itemPosition = recyclerView.getChildPosition(v);
            int tempId = locationData.get(itemPosition).loca_id;

        }
    };

    public void Networking() {
        Call<LocationResult> locationResultCall = service.getLocationResult();
        locationResultCall.enqueue(new Callback<LocationResult>() {
            @Override
            public void onResponse(Call<LocationResult> call, Response<LocationResult> response) {
                if (response.isSuccessful()) {
                    if (response.body().message.equals("Successful Get Location Data")) {
                        locationData = response.body().data;
                        locationAdapter.setAdapter(locationData);
                    }
                }
            }

            @Override
            public void onFailure(Call<LocationResult> call, Throwable t) {
                Log.d("Log", t.getMessage());

            }
        });
    }

}
