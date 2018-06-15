package com.flashmob_team.usr.flashmob_project.Flashmob;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.flashmob_team.usr.flashmob_project.R;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class ReviewFragment extends Fragment {
    Context mContext;
    private ListView listView = null;

    public ReviewFragment(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, null);

        ArrayList<Review> reviews = new ArrayList<>();
        Review review = new Review();
        review.setReviewName("review1");
        review.setReviewTime("2018/06/02");
        review.setReviewContent("너무 재밌었어요!!");
        reviews.add(review);

        Review review2 = new Review();
        review2.setReviewName("review2");
        review2.setReviewTime("2018/06/03");
        review2.setReviewContent("다음에 또 만나고 싶어요ㅎㅎ");
        reviews.add(review2);

        listView = (ListView)view.findViewById(R.id.listView);
        ListAdapter adapter = new ListAdapter(reviews);
        listView.setAdapter(adapter);
        return view;
    }
}
