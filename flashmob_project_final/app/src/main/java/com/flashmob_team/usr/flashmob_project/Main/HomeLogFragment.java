package com.flashmob_team.usr.flashmob_project.Main;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flashmob_team.usr.flashmob_project.Application.ApplicationController;
import com.flashmob_team.usr.flashmob_project.Flashmob.CreateFlashmob;
import com.flashmob_team.usr.flashmob_project.Network.NetworkService;
import com.flashmob_team.usr.flashmob_project.R;
import com.flashmob_team.usr.flashmob_project.SignUp.LocationAdapter;
import com.flashmob_team.usr.flashmob_project.SignUp.LocationData;
import com.flashmob_team.usr.flashmob_project.SignUp.LocationResult;
import com.flashmob_team.usr.flashmob_project.SignUp.SignUpActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
//Fragment --> Fragment Activity
public class HomeLogFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private HomeAdapter homeAdapter;
    private ArrayList<MeetingData> meetingData;

    private RecyclerView recyclerView2;
    private HomeAdapter home2Adapter;
    private LinearLayoutManager linearLayoutManager2;
    private ArrayList<MeetingData> meetingData2;

    private RecyclerView main_recyclerView;
    private HomeMainAdapter homeMainAdapter;
    private LinearLayoutManager linearLayoutManager_main;
    private ArrayList<MeetingData> meetingData_main;


    private int dotscount;
    private ImageView[] dots;
    private NetworkService service;
    int category_id;

    public HomeLogFragment() {
        // Required empty public constructor
    }

    //dp변환 함수
    public int convertPixToDP(int px) {
        int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, getResources().getDisplayMetrics());

        return dp;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        service = ApplicationController.getInstance().getNetworkService();
        category_id = 1;


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        Networking();
        Networking2();
        //main network
        Networking3();


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //floatingActionButton android대신에 app:backgroundcolor해줘야 함.
        final FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.new_btn);
        final ScrollView scrollView = getActivity().findViewById(R.id.scroll_view);

        final int[] previousScrollY = {scrollView.getScrollY()};

        recyclerView = view.findViewById(R.id.home_recyclerView);
        recyclerView2 = view.findViewById(R.id.home_recyclerView2);
        main_recyclerView = view.findViewById(R.id.home_main_recyclerview);

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager2 = new LinearLayoutManager(view.getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager_main = new LinearLayoutManager(view.getContext());
        linearLayoutManager_main.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView2.setHasFixedSize(true);

        main_recyclerView.setLayoutManager(linearLayoutManager_main);
        main_recyclerView.setHasFixedSize(true);

        homeAdapter = new HomeAdapter(getContext(), meetingData);
        home2Adapter = new HomeAdapter(getContext(), meetingData2);
        homeMainAdapter = new HomeMainAdapter(getContext(), meetingData_main);

        recyclerView.setAdapter(homeAdapter);
        recyclerView2.setAdapter(home2Adapter);
        main_recyclerView.setAdapter(homeMainAdapter);

        Networking();
        Networking2();
        //main network
        Networking3();


        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                if (scrollView.getScrollY() > previousScrollY[0] && floatingActionButton.getVisibility() == View.VISIBLE) {
                    floatingActionButton.hide();
                } else if (scrollView.getScrollY() < previousScrollY[0] && floatingActionButton.getVisibility() != View.VISIBLE) {
                    floatingActionButton.show();
                }
                previousScrollY[0] = scrollView.getScrollY();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateFlashmob.class);
                startActivity(intent);
            }
        });

        ArrayList arrayList = new ArrayList();
        int rsID[] = {R.drawable.fish, R.drawable.hwai, R.drawable.tree};
        for (int r = 0; r < 3; r++) {
            arrayList.add(rsID[r]);
        }
//


//        LinearLayout sliderDotspanel = getActivity().findViewById(R.id.slider_dots);
//        dotscount = viewPagerAdapter.getCount();
//        dots = new ImageView[dotscount];

//        for (int i = 0; i < dotscount; i++) {
//            dots[i] = new ImageView(getActivity());
//            dots[i].setImageDrawable(ContextCompat.getDrawable(
//                    getActivity(), R.drawable.nonactive_dot
//            ));
//
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
//            );
//
//            params.setMargins(convertPixToDP(3), 0, convertPixToDP(3), 0);
//            sliderDotspanel.addView(dots[i], params);
//
//        }

//        dots[0].setImageDrawable(ContextCompat.getDrawable(
//                getActivity(), R.drawable.active_dot
//        ));

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                for (int i = 0; i < dotscount; i++) {
//                    dots[i].setImageDrawable(ContextCompat.getDrawable(
//                            getActivity(), R.drawable.nonactive_dot
//                    ));
//
//                    dots[position].setImageDrawable(ContextCompat.getDrawable(
//                            getActivity(), R.drawable.active_dot
//                    ));
//                }
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

    }

    public void Networking() {
        Call<MeetingResult> meetingResultCall = service.getMeetingResult(category_id);
        meetingResultCall.enqueue(new Callback<MeetingResult>() {
            @Override
            public void onResponse(Call<MeetingResult> call, Response<MeetingResult> response) {
                if (response.isSuccessful()) {
                    if (response.body().message.equals("Successful Get Meeting Category List")) {
                        Log.d("Log", "Home network ok");
                        meetingData = response.body().data;
                        homeAdapter.setAdapter(meetingData);

                        Log.d("Log", response.body().data.get(0).meet_title);
                    }
                }
            }

            @Override
            public void onFailure(Call<MeetingResult> call, Throwable t) {
                Log.d("Log", t.getMessage());
            }
        });
    }

    public void Networking2() {
        Call<MeetingResult> meetingResultCall = service.getMeetingResult(2);
        meetingResultCall.enqueue(new Callback<MeetingResult>() {
            @Override
            public void onResponse(Call<MeetingResult> call, Response<MeetingResult> response) {
                if (response.isSuccessful()) {
                    if (response.body().message.equals("Successful Get Meeting Category List")) {
                        Log.d("Log", "Home 2 network ok");
                        meetingData2 = response.body().data;
                        home2Adapter.setAdapter(meetingData2);
                        //Log.d("Log", response.body().data.get(0).meet_title);
                    }
                }
            }

            @Override
            public void onFailure(Call<MeetingResult> call, Throwable t) {
                Log.d("Log", t.getMessage());
            }
        });
    }

    public void Networking3() {
        Call<MeetingResult> meetingResultCall = service.getMeetingResult(3);
        meetingResultCall.enqueue(new Callback<MeetingResult>() {
            @Override
            public void onResponse(Call<MeetingResult> call, Response<MeetingResult> response) {
                if (response.isSuccessful()) {
                    if (response.body().message.equals("Successful Get Meeting Category List")) {
                        Log.d("Log", "Home network ok");
                        meetingData = response.body().data;
                        homeMainAdapter.setAdapter(meetingData);

                        Log.d("Log", response.body().data.get(0).meet_title);
                    }
                }
            }

            @Override
            public void onFailure(Call<MeetingResult> call, Throwable t) {
                Log.d("Log", t.getMessage());
            }
        });
    }
}