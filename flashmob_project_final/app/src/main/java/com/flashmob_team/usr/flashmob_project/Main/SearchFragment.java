package com.flashmob_team.usr.flashmob_project.Main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.flashmob_team.usr.flashmob_project.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment{

    EditText searchEt;
    ListView search_listview;
    List<SearchItem> dataList;
    ArrayList<SearchItem> resultList;
    SearchAdapter searchAdapter;

    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchEt = (EditText)view.findViewById(R.id.searchEt);
        search_listview = (ListView)view.findViewById(R.id.search_listview);

        setData();

        resultList = new ArrayList<>();
        resultList.addAll(dataList);

        searchAdapter = new SearchAdapter(dataList, getContext());
        search_listview.setAdapter(searchAdapter);

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = searchEt.getText().toString();
                search(text);
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setData() {
        dataList = new ArrayList<>();
        dataList.add(new SearchItem("[이태원] 같이 볼링쳐요!!", "2018년 6월 24일", "볼링"));
        dataList.add(new SearchItem("[건대] VR 하실분!", "2018년 6월 24일", "VR"));
        dataList.add(new SearchItem("[잠실] 야구보러가실분있나요?", "2018년 6월 14일", "야구"));
        dataList.add(new SearchItem("[강남] 놀자요!", "2018년 6월 10일", "볼링"));
        dataList.add(new SearchItem("[홍대] VR 하실분~", "2018년 6월 8일", "VR"));
        dataList.add(new SearchItem("[신촌] 방탈출 같이 해여!", "2018년 6월 15일", "방탈출"));
        dataList.add(new SearchItem("[신림] 스터디 구합니다.", "2018년 6월 18일", "스터디"));
        dataList.add(new SearchItem("[대학로] 연극볼사람!!", "2018년 6월 10일","대학로"));
        dataList.add(new SearchItem("[노량진] 같이 쇼핑할 사람~", "2018년 6월 20일", "쇼핑"));
        dataList.add(new SearchItem("[이태원] 같이 볼링쳐요!!", "2018년 6월 24일", "볼링"));
        dataList.add(new SearchItem("[건대] VR 하실분!", "2018년 6월 24일", "VR"));
        dataList.add(new SearchItem("[잠실] 야구보러가실분있나요?", "2018년 6월 14일", "야구"));
        dataList.add(new SearchItem("[강남] 놀자요!", "2018년 6월 10일", "볼링"));
        dataList.add(new SearchItem("[홍대] VR 하실분~", "2018년 6월 8일", "VR"));
        dataList.add(new SearchItem("[신촌] 방탈출 같이 해여!", "2018년 6월 15일", "방탈출"));
        dataList.add(new SearchItem("[신림] 스터디 구합니다.", "2018년 6월 18일", "스터디"));
        dataList.add(new SearchItem("[대학로] 연극볼사람!!", "2018년 6월 10일","대학로"));
        dataList.add(new SearchItem("[노량진] 같이 쇼핑할 사람~", "2018년 6월 20일", "쇼핑"));
    }

    public void search(String charText) {
        dataList.clear();

        if(charText.length() == 0) {
            dataList.addAll(resultList);

        } else {
            for(int i=0; i < resultList.size(); i++) {
                if(resultList.get(i).getTitle().toLowerCase().contains(charText)) {
                    dataList.add(resultList.get(i));
                }
            }
        }

        searchAdapter.notifyDataSetChanged();
    }
}
