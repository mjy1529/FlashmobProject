package com.flashmob_team.usr.flashmob_project.Main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flashmob_team.usr.flashmob_project.R;

import java.util.ArrayList;

public class LikeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerLikeAdapter likeAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<RecyclerLikeItem> likeList;

    public LikeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_like, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.likeRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.scrollToPosition(0);
        likeAdapter = new RecyclerLikeAdapter(likeList);
        mRecyclerView.setAdapter(likeAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    private void initDataset() {
        likeList = new ArrayList<>();
        likeList.add(new RecyclerLikeItem("[이태원] 같이 볼링쳐요!!", "2018/6/24", 5, 5, "이태원"));
        likeList.add(new RecyclerLikeItem("[강남] 야구보러가실분있나요?", "2018/6/14", 9, 10, "강남"));
        likeList.add(new RecyclerLikeItem("[잠실] 같이 쇼핑할 사람~", "2018/6/22", 3, 5, "잠실"));
        likeList.add(new RecyclerLikeItem("[강남] 놀자요!", "2018/6/16", 10, 10, "강남"));
        likeList.add(new RecyclerLikeItem("[신림] 스터디 구합니다.", "2018/6/12", 3, 8, "신림"));
        likeList.add(new RecyclerLikeItem("[신촌] 방탈출 같이 해여!", "2018/6/15", 2, 5, "신촌"));
        likeList.add(new RecyclerLikeItem("[노량진] 같이할 스터디원 구합니다.", "2018/6/25", 4, 5, "노량진"));
        likeList.add(new RecyclerLikeItem("[대학로] 연극볼사람!!", "2018/6/10", 3, 3, "대학로"));
    }
}
