package com.flashmob_team.usr.flashmob_project.Main;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flashmob_team.usr.flashmob_project.Flashmob.CreateFlashmob;
import com.flashmob_team.usr.flashmob_project.Flashmob.InfoActivity;
import com.flashmob_team.usr.flashmob_project.R;
import com.flashmob_team.usr.flashmob_project.SignUp.LocationData;
import com.flashmob_team.usr.flashmob_project.SignUp.SignViewHolder;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    private Context context;
    private ArrayList<MeetingData> meetingData;

    public HomeAdapter(Context context, ArrayList<MeetingData> meetingData) {
        this.context = context;
        this.meetingData = meetingData;
    }

    public void setAdapter(ArrayList<MeetingData> meetingData) {
        this.meetingData = meetingData;
        notifyDataSetChanged();
    }


    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_content, parent, false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(view);

        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, final int position) {
        if (meetingData.get(position).meet_image == null) {
            Glide.with(context)
                    .load(R.drawable.flash_1)
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(holder.VH_home_image);
        }
        else {
            Glide.with(context)
                    .load(meetingData.get(position).meet_image)
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(holder.VH_home_image);
        }

        Log.d("Log", meetingData.get(position).meet_title);
        holder.VH_home_text_title.setText(meetingData.get(position).meet_title);
        holder.VH_home_text_content.setText(meetingData.get(position).meet_memo);

        holder.VH_home_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("title", meetingData.get(position).meet_title);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meetingData != null ? meetingData.size() : 0;
    }
}