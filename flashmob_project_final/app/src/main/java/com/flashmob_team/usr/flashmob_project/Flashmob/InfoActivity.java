package com.flashmob_team.usr.flashmob_project.Flashmob;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flashmob_team.usr.flashmob_project.Application.ApplicationController;
import com.flashmob_team.usr.flashmob_project.Main.MeetingResult;
import com.flashmob_team.usr.flashmob_project.Network.NetworkService;
import com.flashmob_team.usr.flashmob_project.R;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

import cn.refactor.lib.colordialog.ColorDialog;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    LikeButton likeBtn;
    Button joinBtn;
    TextView showTitle;
    TextView showPeoplenum;

    ImageView imageView;

    String date;
    String placeAddress;
    String placeName;
    Double latitude;
    Double longitude;
    String memo;

    boolean checkJoin = false;

    NetworkService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Log.d("Log", "인포액티비티");

        service = ApplicationController.getInstance().getNetworkService();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled( false );
        actionBar.setDisplayShowTitleEnabled( false );

        init();

        Networking();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        likeBtn.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Toast.makeText(InfoActivity.this, "Liked!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {

            }
        });

        //참여하기 버튼을 클릭했을 때 Dialog
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDialog dialog = new ColorDialog(InfoActivity.this);
                dialog.setAnimationEnable(true);
                dialog.setTitle("JOIN");
                //dialog.setColor("#ffd600"); //색깔 변경 가능
                if(!checkJoin) {
                    dialog.setContentText("이 번개 모임에 참여하시겠습니까?");
                    dialog.setPositiveListener("YES", new ColorDialog.OnPositiveListener() {
                        @Override
                        public void onClick(ColorDialog dialog) {
                            Toast.makeText(InfoActivity.this, "신청 완료!", Toast.LENGTH_SHORT).show();
                            joinBtn.setBackground(getDrawable(R.drawable.bottom_btn_border2));
                            dialog.dismiss();
                            checkJoin = true;
                            joinBtn.setText("참여 취소");
                        }
                    });
                    dialog.setNegativeListener("NO", new ColorDialog.OnNegativeListener() {
                        @Override
                        public void onClick(ColorDialog dialog) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                } else {
                    dialog.setContentText("참여를 취소하시겠습니까?");
                    dialog.setPositiveListener("YES", new ColorDialog.OnPositiveListener() {
                        @Override
                        public void onClick(ColorDialog dialog) {
                            Toast.makeText(InfoActivity.this, "취소 완료!", Toast.LENGTH_SHORT).show();
                            joinBtn.setBackground(getDrawable(R.drawable.bottom_btn_border));
                            dialog.dismiss();
                            checkJoin = false;
                            joinBtn.setText("JOIN");
                        }
                    });
                    dialog.setNegativeListener("NO", new ColorDialog.OnNegativeListener() {
                        @Override
                        public void onClick(ColorDialog dialog) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    checkJoin = false;
                }

            }
        });

    }

    public void  Networking() {

        Intent intent = getIntent();


        retrofit2.Call<MeetGetResult> meetingResultCall = service.getMeetingTitleResult(intent.getStringExtra("title"));
        meetingResultCall.enqueue(new Callback<MeetGetResult>() {
            @Override
            public void onResponse(retrofit2.Call<MeetGetResult> call, Response<MeetGetResult> response) {
                if (response.isSuccessful()) {
                    if (response.body().message.equals("Successful Get Meeting Data title")) {
                        Log.d("Log", response.body().data.get(0).meet_title);
                        Log.d("Log", response.body().data.get(0).meet_memo);

                        showTitle.setText(response.body().data.get(0).meet_title);
                        showPeoplenum.setText("people " + response.body().data.get(0).meet_people_num);

                        if (response.body().data.get(0).meet_image == null) {
                            Glide.with(getApplicationContext())
                                    .load(R.drawable.flash_1)
                                    .into(imageView);
                        }
                        else {
                            Glide.with(getApplicationContext())
                                    .load(response.body().data.get(0).meet_image)
                                    .into(imageView);
                        }

                        date = response.body().data.get(0).meet_date;
                        placeName = response.body().data.get(0).meet_place_name;
                        placeAddress = response.body().data.get(0).meet_place_address;
                        latitude = response.body().data.get(0).meet_place_latitude;
                        longitude = response.body().data.get(0).meet_place_longitude;
                        memo = response.body().data.get(0).meet_memo;

                        Log.d("Log", String.valueOf(latitude));
//
//                        Bundle bundle = new Bundle();
//                        bundle.putString("memo", memo);
//                        bundle.putString("date", date);
//                        bundle.putString("placeName", placeName);
//                        bundle.putString("placeAddress", placeAddress);
//                        bundle.putDouble("latitude", latitude);
//                        bundle.putDouble("longitude", longitude);
//                        DetailsFragment detailsFragment = new DetailsFragment(getApplicationContext());
//                        detailsFragment.setArguments(bundle);


                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<MeetGetResult> call, Throwable t) {
                Log.d("Log", t.getMessage());
            }
        });



//        DetailsFragment fragment = new DetailsFragment(getBaseContext());
//        Bundle bundle = new Bundle();
//        bundle.putString("date", date);
//        bundle.putString("placeName", placeName);
//        bundle.putString("placeAddress", placeAddress);
//        bundle.putDouble("latitude", latitude);
//        bundle.putDouble("longitude", longitude);
//        fragment.setArguments(bundle);

        //    public void Networking() {
//        Call<MeetingResult> meetingResultCall = service.getMeetingResult(category_id);
//        meetingResultCall.enqueue(new Callback<MeetingResult>() {
//            @Override
//            public void onResponse(Call<MeetingResult> call, Response<MeetingResult> response) {
//                if (response.isSuccessful()) {
//                    if (response.body().message.equals("Successful Get Meeting Category List")) {
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MeetingResult> call, Throwable t) {
//                Log.d("Log", t.getMessage());
//            }
//        });
//    }


    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_info, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
//
//                    DetailsFragment detailsFragment = new DetailsFragment(InfoActivity.this);
//
//                    Bundle bundle = new Bundle();
//                    bundle.putString("memo", memo);
//                    bundle.putString("date", date);
//                    bundle.putString("placeName", placeName);
//                    bundle.putString("placeAddress", placeAddress);
//                    bundle.putDouble("latitude", latitude);
//                    bundle.putDouble("longitude", longitude);
//                    //DetailsFragment detailsFragment = new DetailsFragment(getApplicationContext());
//                    detailsFragment.setArguments(bundle);
//                    Log.d("Log", "555555555555555555555555");
//
//                    return detailsFragment;
                    return new DetailsFragment(InfoActivity.this);

                case 1:
                    return new MemberFragment(InfoActivity.this);
                case 2:
                    return new ReviewFragment(InfoActivity.this);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public void init() {
        likeBtn = (LikeButton)findViewById(R.id.likeBtn);
        joinBtn = (Button)findViewById(R.id.joinBtn);
        showTitle = (TextView)findViewById(R.id.showTitle);
        showPeoplenum = (TextView)findViewById(R.id.showPeoplenum);
        imageView = (ImageView)findViewById(R.id.sampleImage);
//        Intent intent = getIntent();
       // showTitle.setText(intent.getStringExtra("title"));
        //showPeoplenum.setText("People " + "?" + " / "+ intent.getStringExtra("peoplenum"));

//        String date = intent.getStringExtra("date");
//        intent.getStringExtra("time");
//        intent.getStringExtra("memo");
//        ArrayList<String> cList = intent.getStringArrayListExtra("cList");
//        StringBuffer sb = new StringBuffer();
//        for(int i=0; i<cList.size(); i++) {
//            sb.append("#");
//            sb.append(cList.get(i));
//            sb.append(" ");
//        }
//        intent.getCharSequenceExtra("placeName");
//        intent.getCharSequenceExtra("placeAddress");
//        intent.getDoubleExtra("placeLatitude", 0);
//        intent.getDoubleExtra("placeLongitude", 0);

//        DetailsFragment fragment = new DetailsFragment(getBaseContext());
//        Bundle bundle = new Bundle();
//        bundle.putString("date", date);


    }
}
