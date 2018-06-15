package com.flashmob_team.usr.flashmob_project.Flashmob;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flashmob_team.usr.flashmob_project.R;


@SuppressLint("ValidFragment")
public class MemberFragment extends Fragment {
    Context mContext;

    public MemberFragment(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member, container, false);
        ImageView leaderIv = (ImageView) view.findViewById(R.id.leaderIv);
        leaderIv.setBackground(new ShapeDrawable(new OvalShape()));
        leaderIv.setClipToOutline(true);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int memberCount = 5;
        LinearLayout parentLL, childLL;
        ImageView memberIv;
        TextView memberName;

        LinearLayout memberLinearLayout = (LinearLayout)getActivity().findViewById(R.id.memberLinearLayout);

        int div = 0, remainder = 0;
        if(memberCount % 5 == 0) {
            div = memberCount / 5;
        } else if(memberCount % 5 != 0) {
            div = memberCount / 5 + 1;
        }

        for(int j=1; j<=div; j++) {
            parentLL = new LinearLayout(getActivity());
            LinearLayout.LayoutParams parentLLParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            parentLLParams.setMargins(convertPixToDP(10), 0, convertPixToDP(10), convertPixToDP(10));
            parentLL.setOrientation(LinearLayout.HORIZONTAL);
            parentLL.setWeightSum(5);
            parentLL.setLayoutParams(parentLLParams);

            if(j != div) {
                remainder = 5;
            } else {
                remainder = memberCount % 5;
                parentLLParams.weight = (float)0.8;
                parentLL.setLayoutParams(parentLLParams);
            }

            for(int i=1; i<=remainder; i++) {
                childLL = new LinearLayout(getActivity());
                LinearLayout.LayoutParams childLLParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
                childLL.setOrientation(LinearLayout.VERTICAL);
                childLL.setGravity(Gravity.LEFT);
                childLL.setLayoutParams(childLLParams);

                memberIv = new ImageView(getActivity());
                memberIv.setImageResource(R.drawable.leader);
                memberIv.setBackground(new ShapeDrawable(new OvalShape()));
                memberIv.setClipToOutline(true);

                LinearLayout.LayoutParams memberIvParams = new LinearLayout.LayoutParams(convertPixToDP(50), convertPixToDP(50));
                memberIvParams.setMargins(0,10, 0, 10);
                memberIv.setLayoutParams(memberIvParams);

                childLL.addView(memberIv);

                memberName = new TextView(getActivity());
                memberName.setText("name");
                memberName.setGravity(Gravity.CENTER);

                LinearLayout.LayoutParams memberNameParams = new LinearLayout.LayoutParams(convertPixToDP(50), ViewGroup.LayoutParams.WRAP_CONTENT);
                memberNameParams.setMargins(0, 10, 0, 5);
                memberName.setLayoutParams(memberNameParams);

                childLL.addView(memberName);

                parentLL.addView(childLL);
            }
            memberLinearLayout.addView(parentLL);
        }

    }

    //dp 변환 함수
    public int convertPixToDP(int px) {
        int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, getResources().getDisplayMetrics());
        return dp;
    }
}
