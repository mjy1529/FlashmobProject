package com.flashmob_team.usr.flashmob_project.Main;


import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flashmob_team.usr.flashmob_project.R;

import java.util.ArrayList;

public class UserFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerMypageAdapter mypageAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<RecyclerMypageItem> joinList;

    public UserFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ImageView userIv = (ImageView) view.findViewById(R.id.userIv);
        userIv.setBackground(new ShapeDrawable(new OvalShape()));
        userIv.setClipToOutline(true);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.mypageRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.scrollToPosition(0);
        mypageAdapter = new RecyclerMypageAdapter(joinList);
        mRecyclerView.setAdapter(mypageAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();

    }

    public void initDataset() {
        joinList = new ArrayList<>();
        joinList.add(new RecyclerMypageItem("[이태원] 볼링 번개모임", "2018/06/15", "이태원"));
        joinList.add(new RecyclerMypageItem("[잠실] 야구보러가실분있나요?", "2018/06/13", "잠실"));
        joinList.add(new RecyclerMypageItem("[홍대] VR 하실분~", "2018/06/20", "홍대"));
        joinList.add(new RecyclerMypageItem("[신림] 볼링 번개모임", "2018/06/27", "신림"));
    }
}
