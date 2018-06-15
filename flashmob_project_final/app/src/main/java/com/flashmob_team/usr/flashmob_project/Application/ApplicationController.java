package com.flashmob_team.usr.flashmob_project.Application;

import android.app.Application;

import com.flashmob_team.usr.flashmob_project.Network.NetworkService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationController extends Application {
    private static ApplicationController instance;

    private static String baseUrl = "http://13.125.248.57:3030";


    private NetworkService networkService;
    public static ApplicationController getInstance() {
        return instance;
    }
    public NetworkService getNetworkService() {
        return networkService;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationController.instance = this;
        buildService();
    }

    public void buildService() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        networkService = retrofit.create(NetworkService.class);
    }
}